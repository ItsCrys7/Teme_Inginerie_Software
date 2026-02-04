package com.project.trading.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends Asset {
    // Aici adăug câmpuri specifice pentru acțiuni dacă e nevoie (ex: dividendYield)
}