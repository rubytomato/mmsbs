package com.example.domain.service;

import com.example.domain.entity.Stock;

import java.util.List;

public interface StockService {
    Stock save(Stock stock);
    Stock findOne(Long id);
    List<Stock> findAll();
}
