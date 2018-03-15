package com.example.domain.service;

import com.example.domain.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        TestApplication.class})
@Slf4j
public class TransactionTestServiceTests {

    @Autowired
    private TransactionTestService service;

    @Ignore
    @Transactional(readOnly = true, timeout = 10)
    @Test
    public void check() {
        log.debug("transaction timeout test start >>>");
        service.check(1L, 1, 0L, 0L);
        log.debug("<<< end");
    }

}