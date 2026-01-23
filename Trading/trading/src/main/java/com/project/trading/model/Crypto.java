package com.project.trading.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CRYPTO")
public class Crypto extends Asset {
    // Poți adăuga câmpuri specifice crypto (ex: blockchainNetwork)
}