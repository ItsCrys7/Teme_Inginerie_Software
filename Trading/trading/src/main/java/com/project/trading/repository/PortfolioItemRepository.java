package com.project.trading.repository;

import com.project.trading.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
}