package com.example.domain.service;

import com.example.domain.TestApplication;
import com.example.domain.entity.Stock;
import org.junit.Before;
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
public class StockServiceTests {

    @Autowired
    private StockService service;

    @Before
    public void setup() {
    }

    @Transactional(timeout = 10)
    @Test
    public void save() {
        Stock.Notes notes = Stock.Notes.builder().color("red").shape("triangle").status("waiting").build();
        Stock stock = Stock.builder().name("test").stocks(7).notes(notes).delFlag(Boolean.FALSE).build();
        Stock actual = service.save(stock);
        System.out.println(actual);
    }

    @Transactional(timeout = 10)
    @Test
    public void saveWithoutNotes() {
        Stock stock = Stock.builder().name("test").stocks(3).delFlag(Boolean.FALSE).build();
        Stock actual = service.save(stock);
        System.out.println(actual);
    }

    @Transactional
    @Test
    public void findOne() {
        Stock actual = service.findOne(2L);
        System.out.println(actual);
    }

    @Transactional
    @Test
    public void findAll() {
        List<Stock> stocks = service.findAll();
        stocks.forEach(System.out::println);
    }

}
