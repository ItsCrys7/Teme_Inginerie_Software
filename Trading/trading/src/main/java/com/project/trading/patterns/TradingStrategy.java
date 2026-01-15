package com.project.trading.patterns;

import com.project.trading.model.Asset;
import java.time.LocalDateTime;

public interface TradingStrategy {
    boolean canTrade(Asset asset, LocalDateTime time);
    double calculateFee(double amount, boolean isSameDaySale);
}