package com.example.domain.repository;

import com.example.domain.constant.StandardType;
import com.example.domain.entity.Category;
import com.example.domain.entity.Item;
import com.example.domain.entity.ItemStock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemStockRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemStockRepository repository;

    @Before
    public void setup() {
        System.out.println("setup");

        Category c1 = Category.builder().name("Kitchen & Dining").delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(c1);

        Item i1 = Item.builder().name("Kitchen & Dining - 659dac416e").price(100).salesFrom(LocalDateTime.now()).salesTo(LocalDateTime.now())
                .standardType(StandardType.A).category(c1).delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(i1);

        ItemStock is1 = ItemStock.builder().item(i1).stock(0).delFlag(true).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        ItemStock is2 = ItemStock.builder().item(i1).stock(10).delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        entityManager.persistAndFlush(is1);
        entityManager.persistAndFlush(is2);
    }

    @Test
    public void findAll() {
        List<ItemStock> lists = repository.findAll();
        //lists.forEach(System.out::println);
    }
}
