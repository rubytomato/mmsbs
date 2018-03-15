package com.example.domain.service.impl;

import com.example.domain.entity.PseudoDelay;
import com.example.domain.repository.PseudoDelayRepository;
import com.example.domain.service.TransactionTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TransactionTestServiceImpl implements TransactionTestService {

    @Autowired
    private PseudoDelayRepository repository;

    @Transactional(readOnly = true)
    @Override
    public void check(long beforeSleep, int loopNums, long loopInSleep, long afterSleep) {

        if (beforeSleep > 0L) {
            sleepAs(beforeSleep, "before");
        }

        for (int i=0; i<loopNums; i++) {
            log.debug("loop({}) start", i);

            // 結果が返るまで3秒かかるDBアクセス処理
            PseudoDelay result = repository.findAll().get(0);
            log.debug("result=[{}]", result);

            if (loopInSleep > 0L) {
                sleepAs(loopInSleep, "in loop");
            }

            log.debug("loop end");
        }

        if (afterSleep > 0L) {
            sleepAs(afterSleep, "after");
        }

    }

    private void sleepAs(long sec, String mes) {
        log.debug("sleep start : {}", mes);
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("sleep end : {}", mes);
    }

}
