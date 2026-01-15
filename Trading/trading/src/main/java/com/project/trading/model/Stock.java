package com.project.trading.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends Asset {
    // Aici poți adăuga câmpuri specifice pentru acțiuni dacă e nevoie (ex: dividendYield)
    // Momentan e gol pentru că moștenește totul din Asset
}