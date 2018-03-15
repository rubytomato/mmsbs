package com.example.domain.repository;

import com.example.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>  {

    @Query("SELECT s FROM Stock AS s WHERE FUNCTION('JSON_EXTRACT', s.notes, :key) = :val")
    List<Stock> findByNotes(@Param("key") String key, @Param("val") String val);

}
