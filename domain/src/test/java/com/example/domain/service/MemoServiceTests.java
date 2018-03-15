package com.example.domain.service;

import com.example.common.config.CommonConfigure;
import com.example.common.util.WeatherForecast;
import com.example.domain.config.DomainConfigure;
import com.example.domain.entity.Memo;
import com.example.domain.repository.MemoRepository;
import com.example.domain.service.impl.MemoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

public class MemoServiceTests {

    @Mock
    private MemoRepository repository;
    @Spy
    private WeatherForecast weatherForecast;

    @InjectMocks
    private MemoServiceImpl sut;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        DomainConfigure config = new DomainConfigure();
        config.setKey1("bean_domain_c");
        config.setKey2("bean_domain_d");
        Whitebox.setInternalState(sut, "config", config);

        CommonConfigure commonConfigure = new CommonConfigure();
        commonConfigure.setDatePattern("yyyy-MM-dd");
        Whitebox.setInternalState(weatherForecast, "config", commonConfigure);
    }

    @Test
    public void test_findById() {
        Memo expected = Memo.builder().id(1L).title("memo").description("memo description").done(false).updated(LocalDateTime.now()).build();
        Mockito.when(repository.findOne(Mockito.anyLong())).thenReturn(expected);

        Memo actual = sut.findById(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test_findByTitle() {
        Memo m1 = Memo.builder().id(2L).title("memo job").description("memo2 description").done(false).updated(LocalDateTime.now()).build();
        Memo m2 = Memo.builder().id(4L).title("memo job").description("memo4 description").done(false).updated(LocalDateTime.now()).build();
        List<Memo> memos = Arrays.asList(m2, m1);

        Page<Memo> expected = new PageImpl<>(memos);
        expected.getContent().forEach(System.out::println);

        System.out.println();

        String title = "job";
        Pageable page = new PageRequest(0,3, Sort.Direction.DESC, "id");
        Mockito.when(repository.findByTitleLike(eq("%" + title + "%"), eq(page))).thenReturn(expected);

        Page<Memo> actual = sut.findByTitle(title, page);

        assertThat(actual.getContent()).isEqualTo(expected.getContent());
    }

    @Test
    public void test_registerWeatherMemo() {
        LocalDate date = LocalDate.of(2017, 9, 20);

        Mockito.when(weatherForecast.report(date)).thenReturn("weather forecast : test-test-test-2017-09-20");

        Memo expected = Memo.builder().id(1L).title("weather memo :").description("weather forecast : sunny").done(false).updated(LocalDateTime.now()).build();
        Mockito.when(repository.saveAndFlush(any(Memo.class))).thenReturn(expected);

        Memo actual = sut.registerWeatherMemo(date);

        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }
}
