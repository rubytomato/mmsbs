package com.example.domain.dao;

import com.example.domain.TestApplication;
import com.example.domain.dto.MemoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
    TestApplication.class})
public class MemoDaoTest {

    @Autowired
    private MemoDao memoDao;

    @Transactional
    @Test
    public void test() {
        List<MemoDto> memoDtos = memoDao.findAll();
        //memoDtos.forEach(System.out::println);
    }
}
