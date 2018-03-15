package com.example.domain.repository;

import com.example.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemoRepositoryJoinTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MemoRepository sut;

    @Transactional
    @Test
    @Sql(statements = {
        "INSERT INTO memo (id, title, description, done) VALUES (99999, 'memo test', 'memo description', TRUE)"
    })
    public void test_findOne() {
        Memo expected = entityManager.find(Memo.class, 99999L);

        Memo actual = sut.findOne(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Transactional
    @Test
    public void test_save() {
        Memo expected = Memo.builder().title("memo").description("memo description").build();
        sut.saveAndFlush(expected);
        entityManager.clear();

        Memo actual = entityManager.find(Memo.class, expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Transactional
    @Test
    public void test_findByTitleLike() {
        Memo m1 = entityManager.find(Memo.class, 2L);
        Memo m2 = entityManager.find(Memo.class, 4L);
        List<Memo> expected = Arrays.asList(m2, m1);

        Pageable page = new PageRequest(0, 3, Sort.Direction.DESC, "id");
        Page<Memo> actual = sut.findByTitleLike("%job%", page);

        actual.getContent().forEach(System.out::println);
        assertThat(actual.getContent()).isEqualTo(expected);
    }

}
