package com.project.trading.repository;

import com.project.trading.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}