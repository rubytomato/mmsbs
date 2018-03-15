package com.example.domain.entity;

import com.example.domain.StopwatchRule;
import com.example.domain.TestApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
public class MemoWithEntityManagerTest {

    @Rule
    public final Stopwatch stopwatch = StopwatchRule.create();

    @Autowired
    private EntityManager entityManager;

    private List<Memo> lists;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:30}")
    private int batchSize;

    @Before
    public void setup() {
        lists = new ArrayList<>();
        for (int i=0; i<100_000; i++) {
            String title = UUID.randomUUID().toString();
            String description = i + " : " + title;
            lists.add(Memo.of(title, description));
        }
    }

    @Test
    @Transactional
    public void testGetResultList() {
        TypedQuery<Memo> query = entityManager.createQuery("select m from Memo AS m", Memo.class);
        List<Memo> lists = query.getResultList();
        //lists.forEach(System.out::println);
    }

    @Ignore
    @Test
    @Transactional
    public void testPersist() {
        System.out.println("entityManager.persist start");

        lists.forEach(memo -> {
            entityManager.persist(memo);
            entityManager.flush();
            entityManager.clear();
        });

        System.out.println("entityManager.persist end");
    }

    @Ignore
    @Test
    @Transactional
    public void testBatchModet() {
        System.out.println("entityManager.persist start");

        for (int i=0; i<lists.size(); i++) {
            entityManager.persist(lists.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();

        System.out.println("entityManager.persist end");
    }

}
