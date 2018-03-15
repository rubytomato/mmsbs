package com.example.domain.repository;

import com.example.domain.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStockRepository extends JpaRepository<ItemStock, Long> {
}
