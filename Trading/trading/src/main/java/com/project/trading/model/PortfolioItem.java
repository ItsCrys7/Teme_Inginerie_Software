package com.project.trading.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Asset asset;

    private Integer quantity;
    private Double buyPrice; // Pentru calcul profit
    private LocalDateTime purchaseDate;
    private String strategyType; // DAY_TRADING, LONG_TERM
}