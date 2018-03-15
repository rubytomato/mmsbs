package com.example.domain.repository;

import com.example.domain.entity.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockRepository repository;

    @Before
    public void setup() {
        Stock.Notes n1 = Stock.Notes.builder().color("red").shape("triangle").status("done").build();
        Stock s1 = Stock.builder().name("test").stocks(1).notes(n1).delFlag(Boolean.FALSE).build();
        entityManager.persistAndFlush(s1);

        Stock.Notes n2 = Stock.Notes.builder().color("blue").build();
        Stock s2 = Stock.builder().name("test").stocks(2).notes(n2).delFlag(Boolean.FALSE).build();
        entityManager.persistAndFlush(s2);

        Stock.Notes n3 = Stock.Notes.builder().color("green").status("waiting").build();
        Stock s3 = Stock.builder().name("test").stocks(3).notes(n3).delFlag(Boolean.FALSE).build();
        entityManager.persistAndFlush(s3);

        Stock s4 = Stock.builder().name("test").stocks(4).delFlag(Boolean.FALSE).build();
        entityManager.persistAndFlush(s4);

        entityManager.clear();
    }

    @Test
    public void findOne() {
        Stock stock = repository.findOne(1L);
        //System.out.println(stock);
    }

    @Test
    public void findAll() {
        List<Stock> stocks = repository.findAll();
        //stocks.forEach(System.out::println);
    }

    @Test
    public void save() {
        Stock.Notes notes = Stock.Notes.builder().color("red").shape("circle").status("waiting").build();
        Stock stock = Stock.builder().name("test").stocks(10).delFlag(false).notes(notes).build();
        repository.save(stock);
        repository.flush();

        Stock actual = entityManager.find(Stock.class, stock.getId());
        System.out.println(actual);
        assertThat(actual).isEqualTo(stock);
    }

}
