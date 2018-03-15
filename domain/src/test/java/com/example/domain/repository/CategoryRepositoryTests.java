package com.example.domain.repository;

import com.example.domain.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
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
public class CategoryRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository repository;

    @Before
    public void setup() {
        System.out.println("setup");

        Category c1 = Category.builder().name("Kitchen & Dining").delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        Category c2 = Category.builder().name("FURNITURE").delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();
        Category c3 = Category.builder().name("BEDDING & BATH").delFlag(false).createAt(LocalDateTime.now()).updateAt(LocalDateTime.now()).build();

        entityManager.persistAndFlush(c1);
        entityManager.persistAndFlush(c2);
        entityManager.persistAndFlush(c3);
    }

    @Test
    public void findAll() {
        List<Category> lists = repository.findAll();
        //lists.forEach(System.out::println);
    }

    @Ignore
    @Test
    public void save() {
    }

}
