package com.project.trading.patterns;

import com.project.trading.model.Asset;
import com.project.trading.model.Stock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

// Strategie pentru Actiuni (restrictii orare)
public class StockStrategy implements TradingStrategy {
    @Override
    public boolean canTrade(Asset asset, LocalDateTime time) {
        if (!(asset instanceof Stock)) return true; // Crypto e liber
        
        DayOfWeek day = time.getDayOfWeek();
        int hour = time.getHour();
        // Luni-Vineri, 9-18
        boolean isWeekday = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
        boolean isWorkingHours = hour >= 9 && hour < 22;
        
        return isWeekday && isWorkingHours;
    }

    @Override
    public double calculateFee(double amount, boolean isSameDaySale) {
        // Day Trading logic: daca e vandut in aceeasi zi, taxa normala, 
        // DACA a fost marcat ca DayTrade si NU e vandut, se aplica penalizarea extern (batch job).
        // Aici punem o taxa standard de tranzactie (ex 0.1%)
        return amount * 0.001; 
    }
}