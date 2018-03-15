package com.example.domain.service;

import com.example.common.util.WeatherForecast;
import com.example.domain.TestApplication;
import com.example.domain.entity.Memo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
@TestPropertySource(properties = {
    "custom.common.datePattern=yyyy-MM-dd"
})
@DirtiesContext
public class MemoServiceJoinTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MemoService sut;

    @SpyBean
    private WeatherForecast weatherForecast;

    @Transactional
    @Test
    public void test_findById() {
        Long id = 1L;
        Memo expected = entityManager.find(Memo.class, id);

        Memo actual = sut.findById(id);

        assertThat(actual).isEqualTo(expected);
    }

    @Transactional
    @Test
    public void test_findByTitle() {
        Memo m1 = entityManager.find(Memo.class, 2L);
        Memo m2 = entityManager.find(Memo.class, 4L);
        List<Memo> expected = Arrays.asList(m2, m1);

        Pageable page = new PageRequest(0,3, Sort.Direction.DESC, "id");
        Page<Memo> actual = sut.findByTitle("job", page);

        actual.getContent().forEach(System.out::println);
        assertThat(actual.getContent()).isEqualTo(expected);
    }

    @Transactional
    @Test
    public void test_registerWeatherMemo() {
        LocalDate date = LocalDate.of(2017,9,20);

        Mockito.when(weatherForecast.report(date)).thenReturn("weather forecast : test-test-test");

        Memo actual = sut.registerWeatherMemo(date);
        System.out.println("memo : " + actual.toString());

        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getTitle()).isEqualTo("weather memo : [2017-09-20]");
        assertThat(actual.getDescription()).isEqualTo("weather forecast : test-test-test");
    }

}