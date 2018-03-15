package com.example.domain.dao;

import com.example.domain.TestApplication;
import com.example.domain.entity.Stock;
import com.example.domain.repository.StockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
public class StockDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StockDao dao;

    @Autowired
    private StockRepository repository;

    @Transactional(readOnly = true)
    @Test
    public void findOne() {
        Stock stock = dao.findOne(1L);
        //System.out.println(stock);
    }

    @Transactional(readOnly = true)
    @Test
    public void findAll() {
        List<Stock> lists = dao.findAll();
        //lists.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @Test
    public void queryByJson() {
        String sql = "SELECT s " +
                      " FROM Stock AS s " +
                     " WHERE s.id > 0 " +
                       " AND FUNCTION('JSON_EXTRACT', s.notes, '$.color') = :color";

        TypedQuery<Stock> query = entityManager.createQuery(sql, Stock.class)
                .setParameter("color", "red");

        List<Stock> lists = query.getResultList();
        //lists.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @Test
    public void findByNotes() {
        String sql = "SELECT s " +
                    " FROM Stock AS s " +
                    " WHERE FUNCTION('JSON_EXTRACT', s.notes, :key) = :val";

        TypedQuery<Stock> query = entityManager.createQuery(sql, Stock.class)
                .setParameter("key", "$.color")
                .setParameter("val", "red");

        List<Stock> stocks = query.getResultList();
        //stocks.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @Test
    public void findByNotesWithRepository() {
        List<Stock> stocks = repository.findByNotes("$.color", "red");
        //stocks.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @Test
    public void findOneWithRepository() {
        Stock stock = repository.findOne(14L);
        //System.out.println(stock);
    }

    @Transactional(readOnly = true)
    @Test
    public void findAllWithRepository() {
        List<Stock> stocks = repository.findAll();
        //stocks.forEach(System.out::println);
    }

    @Transactional(readOnly = false)
    @Test
    public void save() {
        Stock.Notes notes = Stock.Notes.builder().color("cyan").status("done").build();
        Stock stock = Stock.builder().name("あんずぼー").stocks(1).notes(notes).delFlag(false).build();
        repository.saveAndFlush(stock);
    }

}
