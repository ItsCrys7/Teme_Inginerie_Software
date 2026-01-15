package com.project.trading.patterns;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PriceNotifier {
    private List<String> notifications = new ArrayList<>();

    public void notifyUpdate(String symbol, double oldPrice, double newPrice) {
        String message = "ALERT: " + symbol + " changed from " + oldPrice + " to " + newPrice;
        notifications.add(message);
        System.out.println(message); // Simulare notificare
    }

    public List<String> getNotifications() {
        return notifications;
    }
}