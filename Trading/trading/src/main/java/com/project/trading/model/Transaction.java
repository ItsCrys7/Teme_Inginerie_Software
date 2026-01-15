package com.project.trading.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String assetSymbol;
    private String type; // BUY / SELL
    private Integer quantity;
    private Double price;
    private LocalDateTime timestamp;
}