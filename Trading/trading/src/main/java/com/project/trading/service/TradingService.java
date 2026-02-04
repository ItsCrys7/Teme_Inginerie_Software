package com.project.trading.service;

import com.project.trading.model.*;
import com.project.trading.patterns.*;
import com.project.trading.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradingService {

    @Autowired private UserRepository userRepo;
    @Autowired private AssetRepository assetRepo;
    @Autowired private PortfolioItemRepository portfolioRepo;
    @Autowired private TransactionRepository transRepo;
    @Autowired private PriceNotifier notifier;

    private TradingStrategy stockStrategy = new StockStrategy();

    public void initData() {
        if(assetRepo.count() == 0) {
            assetRepo.save(AssetFactory.createAsset("STOCK", "AAPL", "Apple", 150.0));
            assetRepo.save(AssetFactory.createAsset("STOCK", "TSLA", "Tesla", 200.0));
            assetRepo.save(AssetFactory.createAsset("CRYPTO", "BTC", "Bitcoin", 45000.0));
            assetRepo.save(AssetFactory.createAsset("CRYPTO", "ETH", "Ethereum", 3000.0));
            
            User u = new User();
            u.setUsername("student");
            u.setPassword("pass");
            u.setBalance(10000.0);
            userRepo.save(u);
        }
    }

    @Transactional
    // Buy : Verifică banii
    // Cheamă stockStrategy.canTrade (dacă e închis, dă eroare).
    // Scade banii, creează PortfolioItem
    public String buy(Long userId, Long assetId, int quantity, String strategy) {
        User user = userRepo.findById(userId).orElseThrow();
        Asset asset = assetRepo.findById(assetId).orElseThrow();

        if (asset instanceof Stock && !stockStrategy.canTrade(asset, LocalDateTime.now())) {
            return "Market is closed for Stocks (Open M-F 09-18).";
        }

        double cost = asset.getCurrentPrice() * quantity;
        if (user.getBalance() < cost) return "Insufficient funds.";

        user.setBalance(user.getBalance() - cost);
        
        PortfolioItem item = new PortfolioItem();
        item.setUser(user);
        item.setAsset(asset);
        item.setQuantity(quantity);
        item.setBuyPrice(asset.getCurrentPrice());
        item.setPurchaseDate(LocalDateTime.now());
        item.setStrategyType(strategy); 
        portfolioRepo.save(item);

        recordTransaction(user, asset, "BUY", quantity, asset.getCurrentPrice());
        return "Bought " + asset.getSymbol();
    }

    @Transactional
    // Sell : Vinde, șterge itemul din portofoliu, dă banii înapoi
    public String sell(Long userId, Long portfolioItemId) { 
        PortfolioItem item = portfolioRepo.findById(portfolioItemId).orElseThrow();
        Asset asset = item.getAsset();
        User user = item.getUser();

        if (asset instanceof Stock && !stockStrategy.canTrade(asset, LocalDateTime.now())) {
            return "Market is closed.";
        }

        double value = asset.getCurrentPrice() * item.getQuantity();
        user.setBalance(user.getBalance() + value);
        portfolioRepo.delete(item);

        recordTransaction(user, asset, "SELL", item.getQuantity(), asset.getCurrentPrice());
        return "Sold successfully.";
    }

    @Transactional
    public void setAutoTradeLimits(Long assetId, Double buyLimit, Double sellLimit) {
        Asset a = assetRepo.findById(assetId).orElseThrow();
        a.setAutoBuyPrice(buyLimit);
        a.setAutoSellPrice(sellLimit);
        assetRepo.save(a);
    }
// Simulează schimbarea pieței, declanșează cumpărări/vânzări automate și notificări
    public void simulateMarketChange() {
        List<Asset> assets = assetRepo.findAll();
        User user = userRepo.findById(1L).orElse(null);
        if(user == null) return;

        for (Asset a : assets) {
            double oldPrice = a.getCurrentPrice();
            double change = (Math.random() - 0.5) * 5; 
            double newPrice = oldPrice + change;
            if (newPrice < 0.1) newPrice = 0.1;
            
            a.setCurrentPrice(newPrice);
            assetRepo.save(a);

            // Auto Buy
            if (a.getAutoBuyPrice() != null && newPrice < a.getAutoBuyPrice()) {
                if (user.getBalance() >= newPrice) {
                    buy(user.getId(), a.getId(), 1, "LONG");
                }
            }
            // Auto Sell
            if (a.getAutoSellPrice() != null && newPrice > a.getAutoSellPrice()) {
                List<PortfolioItem> items = user.getPortfolio();
                for (PortfolioItem item : items) {
                    if (item.getAsset().getId().equals(a.getId())) {
                        sell(user.getId(), item.getId());
                    }
                }
            }
            // Observer Notif
            if (Math.abs(change) > 1.0) {
                notifier.notifyUpdate(a.getSymbol(), oldPrice, newPrice);
            }
        }
    }

    @Transactional
    public String applyDayTradingCheck() {
        User user = userRepo.findById(1L).orElseThrow();
        List<PortfolioItem> items = user.getPortfolio();
        int count = 0;

        for (PortfolioItem item : items) {
            // Daca e strategie DAY si NU e cumparat azi -> Penalizare 5%
            if ("DAY".equals(item.getStrategyType()) && 
                item.getPurchaseDate().toLocalDate().isBefore(java.time.LocalDate.now())) {
                
                double currentValue = item.getAsset().getCurrentPrice() * item.getQuantity();
                double penalty = currentValue * 0.05;
                
                user.setBalance(user.getBalance() - penalty);
                item.setStrategyType("EXPIRED_DAY"); // Ca sa nu taxam la infinit
                portfolioRepo.save(item);
                count++;
            }
        }
        userRepo.save(user);
        return count > 0 ? "Penalties applied to " + count + " items." : "No penalties needed.";
    }

    private void recordTransaction(User u, Asset a, String type, int qty, double price) {
        Transaction t = new Transaction();
        t.setUserId(u.getId());
        t.setAssetSymbol(a.getSymbol());
        t.setType(type);
        t.setQuantity(qty);
        t.setPrice(price);
        t.setTimestamp(LocalDateTime.now());
        transRepo.save(t);
    }
}