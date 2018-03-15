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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
public class MemoWithJdbcTemplateTest {

    @Rule
    public final Stopwatch stopwatch = StopwatchRule.create();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Memo> lists;

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
    public void testQueryForList() {
        @SuppressWarnings("unchecked")
        BeanPropertyRowMapper<Memo> mapper = new BeanPropertyRowMapper(Memo.class);
        String sql = "select * from memo";

        List<Memo> lists = jdbcTemplate.query(sql, mapper);

        //lists.forEach(System.out::println);
    }

    @Ignore
    @Test
    @Transactional
    public void testBatchUpdate() {

        String sql = "insert into memo (title, description, done, updated) values (?, ?, ?, ?)";

        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Memo memo = lists.get(i);
                ps.setString(1, memo.getTitle());
                ps.setString(2, memo.getDescription());
                ps.setBoolean(3, memo.getDone());
                ps.setTimestamp(4, java.sql.Timestamp.valueOf(memo.getUpdated()));
            }
            @Override
            public int getBatchSize() {
                return lists.size();
            }
        };

        jdbcTemplate.batchUpdate(sql, setter);
    }

}
