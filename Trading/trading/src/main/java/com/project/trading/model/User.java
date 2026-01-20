package com.project.trading.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "app_user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password; 
    private Double balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PortfolioItem> portfolio;
}