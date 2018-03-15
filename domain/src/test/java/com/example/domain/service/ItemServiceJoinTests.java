package com.example.domain.service;

import com.example.domain.TestApplication;
import com.example.domain.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        TestApplication.class})
public class ItemServiceJoinTests {

    @Autowired
    private ItemService itemService;

    @Test
    @Transactional
    public void find() {
        List<Item> items = itemService.find();
        //items.forEach(System.out::println);
    }

}