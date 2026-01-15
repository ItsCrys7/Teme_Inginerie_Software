package com.project.trading.service;

import com.project.trading.repository.*;
import com.project.trading.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired private TradingService service;
    @Autowired private AssetRepository assetRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transRepo;
    @Autowired private com.project.trading.patterns.PriceNotifier notifier;

    private Long currentUserId = 1L; 

    @GetMapping("/")
    public String dashboard(Model model) {
        service.initData();
        User user = userRepo.findById(currentUserId).get();
        
        model.addAttribute("user", user);
        model.addAttribute("assets", assetRepo.findAll());
        model.addAttribute("portfolio", user.getPortfolio());
        model.addAttribute("history", transRepo.findAll());
        model.addAttribute("notifications", notifier.getNotifications());
        return "index";
    }

    @PostMapping("/buy")
    public String buy(@RequestParam Long assetId, @RequestParam int quantity, @RequestParam String strategy) {
        service.buy(currentUserId, assetId, quantity, strategy);
        return "redirect:/";
    }

    @PostMapping("/sell")
    public String sell(@RequestParam Long itemId) {
        service.sell(currentUserId, itemId);
        return "redirect:/";
    }

    @PostMapping("/simulate")
    public String simulate() {
        service.simulateMarketChange();
        return "redirect:/";
    }

    @PostMapping("/setLimits")
    public String setLimits(@RequestParam Long assetId, 
                            @RequestParam(required=false) Double buyLimit, 
                            @RequestParam(required=false) Double sellLimit) {
        service.setAutoTradeLimits(assetId, buyLimit, sellLimit);
        return "redirect:/";
    }

    @PostMapping("/endDay")
    public String endDay() {
        service.applyDayTradingCheck();
        return "redirect:/";
    }
}