package com.example.domain.service.impl;

import com.example.domain.entity.Item;
import com.example.domain.repository.ItemRepository;
import com.example.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public List<Item> find() {
        return itemRepository.findAll();
    }

}
