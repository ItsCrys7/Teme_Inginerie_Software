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

    // Initializare date (Ruleaza la start)
    public void initData() {
        if(assetRepo.count() == 0) {
            assetRepo.save(AssetFactory.createAsset("STOCK", "AAPL", "Apple", 150.0));
            assetRepo.save(AssetFactory.createAsset("STOCK", "TSLA", "Tesla", 200.0));
            assetRepo.save(AssetFactory.createAsset("CRYPTO", "BTC", "Bitcoin", 45000.0));
            
            User u = new User();
            u.setUsername("student");
            u.setPassword("pass");
            u.setBalance(10000.0);
            userRepo.save(u);
        }
    }

    @Transactional
    public String buy(Long userId, Long assetId, int quantity, String strategy) {
        User user = userRepo.findById(userId).orElseThrow();
        Asset asset = assetRepo.findById(assetId).orElseThrow();

        // Strategy Check (Time)
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
        item.setStrategyType(strategy); // "DAY" or "LONG"
        portfolioRepo.save(item);

        recordTransaction(user, asset, "BUY", quantity, asset.getCurrentPrice());
        return "Success";
    }

    @Transactional
    public String sell(Long userId, Long portfolioItemId) {
        PortfolioItem item = portfolioRepo.findById(portfolioItemId).orElseThrow();
        Asset asset = item.getAsset();
        User user = item.getUser();

        // Strategy Check
        if (asset instanceof Stock && !stockStrategy.canTrade(asset, LocalDateTime.now())) {
            return "Market is closed.";
        }

        double value = asset.getCurrentPrice() * item.getQuantity();
        
        // Day Trading Logic: Penalizare daca a fost cumparat DayTrade dar vandut > 1 zi?
        // SAU invers: cerinta zice "Once a stock is bought as day trading and is not sold on the same day, it will charge 5%"
        // Aici verificam la vanzare daca e "same day".
        // Daca a trecut ziua si a fost marcat DAY_TRADING, consideram ca penalizarea se aplica automat (vezi updatePrices).
        
        user.setBalance(user.getBalance() + value);
        portfolioRepo.delete(item);

        recordTransaction(user, asset, "SELL", item.getQuantity(), asset.getCurrentPrice());
        return "Sold successfully.";
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

    // Simulare schimbare pret + Observer
    public void simulateMarketChange() {
        List<Asset> assets = assetRepo.findAll();
        for (Asset a : assets) {
            double oldPrice = a.getCurrentPrice();
            double change = (Math.random() - 0.5) * 2; // +/- 1 unit random
            a.setCurrentPrice(oldPrice + change);
            assetRepo.save(a);
            
            // Observer Trigger
            if (Math.abs(change) > 0.5) {
                notifier.notifyUpdate(a.getSymbol(), oldPrice, a.getCurrentPrice());
            }
            
            // Aici s-ar implementa si Auto-Buy/Sell logic (Limit Orders)
        }
    }
}