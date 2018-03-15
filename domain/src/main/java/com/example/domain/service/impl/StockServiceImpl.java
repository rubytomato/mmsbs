package com.example.domain.service.impl;

import com.example.domain.entity.Stock;
import com.example.domain.repository.StockRepository;
import com.example.domain.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository repository;

    @Override
    public Stock save(Stock stock) {
        return repository.saveAndFlush(stock);
    }

    @Override
    public Stock findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Stock> findAll() {
        return repository.findAll();
    }
}