package com.example.domain.repository;

import com.example.domain.entity.Memo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemoRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MemoRepository sut;

    @BeforeTransaction
    public void init() {
        // トランザクション開始前に実行されます
        System.out.println("1. init");
    }
    @Before
    public void setUp() {
        // トランザクション開始後、テストメソッド開始前に実行されます
        System.out.println("2. setUp");
    }
    @After
    public void tearDown() {
        // テストメソッド終了後、トランザクション終了前に実行されます
        System.out.println("3. tearDown");
    }
    @AfterTransaction
    public void clear() {
        // トランザクション終了後に実行されます
        System.out.println("4. clear");
    }

    @Test
    @Sql(statements = {
        "INSERT INTO memo (id, title, description, done, updated) VALUES (99999, 'memo test', 'memo description', FALSE, CURRENT_TIMESTAMP)"
    })
    public void test_findOne() {
        Memo expected = entityManager.find(Memo.class, 99999L);

        Memo actual = sut.findOne(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test_save() {
        Memo expected = Memo.builder().title("memo").description("memo description").build();
        sut.saveAndFlush(expected);
        entityManager.clear();

        Memo actual = entityManager.find(Memo.class, expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test_findByTitleLike() {
        Memo m1 = Memo.builder().title("memo shopping").description("memo1 description").done(false).updated(LocalDateTime.now()).build();
        entityManager.persistAndFlush(m1);
        Memo m2 = Memo.builder().title("memo job").description("memo2 description").done(false).updated(LocalDateTime.now()).build();
        entityManager.persistAndFlush(m2);
        Memo m3 = Memo.builder().title("memo private").description("memo3 description").done(false).updated(LocalDateTime.now()).build();
        entityManager.persistAndFlush(m3);
        Memo m4 = Memo.builder().title("memo job").description("memo4 description").done(false).updated(LocalDateTime.now()).build();
        entityManager.persistAndFlush(m4);
        Memo m5 = Memo.builder().title("memo private").description("memo5 description").done(false).updated(LocalDateTime.now()).build();
        entityManager.persistAndFlush(m5);

        entityManager.clear();

        List<Memo> expected = Arrays.asList(m4, m2);

        Pageable page = new PageRequest(0, 3, Sort.Direction.DESC, "id");
        Page<Memo> actual = sut.findByTitleLike("%job%", page);

        assertThat(actual.getContent()).isEqualTo(expected);
    }

}
