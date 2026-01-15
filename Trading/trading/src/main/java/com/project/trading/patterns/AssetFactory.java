package com.project.trading.patterns;

import com.project.trading.model.Asset;
import com.project.trading.model.Crypto;
import com.project.trading.model.Stock;

public class AssetFactory {
    public static Asset createAsset(String type, String symbol, String name, Double price) {
        if (type.equalsIgnoreCase("STOCK")) {
            Stock s = new Stock();
            s.setSymbol(symbol); s.setName(name); s.setCurrentPrice(price);
            return s;
        } else if (type.equalsIgnoreCase("CRYPTO")) {
            Crypto c = new Crypto();
            c.setSymbol(symbol); c.setName(name); c.setCurrentPrice(price);
            return c;
        }
        throw new IllegalArgumentException("Unknown asset type");
    }
}