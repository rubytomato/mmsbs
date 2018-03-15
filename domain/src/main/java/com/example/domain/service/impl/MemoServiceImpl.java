package com.example.domain.service.impl;

import com.example.common.util.WeatherForecast;
import com.example.domain.config.DomainConfigure;
import com.example.domain.entity.Memo;
import com.example.domain.repository.MemoRepository;
import com.example.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
public class MemoServiceImpl implements MemoService {

    @Autowired
    private DomainConfigure config;
    @Autowired
    private MemoRepository memoRepository;
    @Autowired
    private WeatherForecast weatherForecast;

    @Transactional(readOnly = true)
    @Override
    public Memo findById(Long id) {
        log.info("findById - id:{}, config.key1:{}, config.key2:{}", id, config.getKey1(), config.getKey2());
        return memoRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Memo> findByTitle(String title, Pageable page) {
        log.info("findByTitle - title:{}, page:{}, config.key1:{}, config.key2:{}", title, page, config.getKey1(), config.getKey2());
        return memoRepository.findByTitleLike(String.join("","%", title, "%"), page);
    }

    @Transactional(timeout = 10)
    @Override
    public Memo registerWeatherMemo(LocalDate date) {
        log.info("registerWeatherMemo - date:{}", date);
        String title = "weather memo : [" + weatherForecast.getReportDayStringValue(date) + "]";
        String description = weatherForecast.report(date);
        log.info("registerWeatherMemo - description:{}", description);
        Memo memo = Memo.builder().title(title).description(description).build();
        return memoRepository.saveAndFlush(memo);
    }

}