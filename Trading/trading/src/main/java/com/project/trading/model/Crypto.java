package com.project.trading.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CRYPTO")
public class Crypto extends Asset {
    // La fel, poți adăuga câmpuri specifice crypto (ex: blockchainNetwork)
}