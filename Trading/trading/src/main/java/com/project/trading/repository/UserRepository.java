package com.project.trading.repository;
import com.project.trading.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
// Fa la fel si pentru AssetRepository, PortfolioItemRepository, TransactionRepository
// Doar schimba clasa din <User, Long> in <Asset, Long> etc.