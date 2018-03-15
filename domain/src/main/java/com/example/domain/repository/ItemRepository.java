package com.example.domain.repository;

import com.example.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Class-based projections用の検索メソッド
    <T> T findOneById(Long id, Class<T> type);
}
