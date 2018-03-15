package com.example.application.controller;

import com.example.application.config.AppConfigure;
import com.example.application.vo.MemoView;
import com.example.domain.entity.Memo;
import com.example.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "memo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class MemoController {

    @Autowired
    private MemoService service;
    @Autowired
    private AppConfigure config;
    @Value("${custom.application.key3}")
    private String key3;

    @PostConstruct
    public void init() {
        log.info("MemoController init : config.key1:{}, config.key2:{}, key3:{}", config.getKey1(), config.getKey2(), key3);
    }

    @GetMapping(path = "id/{id}")
    public ResponseEntity<MemoView> id(@PathVariable(value = "id") Long id) {
        log.info("id - id:{}, config.key1:{}, config.key2:{}, key3:{}", id, config.getKey1(), config.getKey2(), key3);
        Memo memo = service.findById(id);
        return new ResponseEntity<>(convert(memo), HttpStatus.OK);
    }

    // ResponseEntityを使用しないパターン
    @GetMapping(path = "id2/{id}")
    @ResponseBody
    public MemoView id2(@PathVariable(value = "id") Long id) {
        log.info("id2 - id:{}, config.key1:{}, config.key2:{}, key3:{}", id, config.getKey1(), config.getKey2(), key3);
        Memo memo = service.findById(id);
        return convert(memo);
    }

    @GetMapping(path = "title/{title}")
    public ResponseEntity<List<MemoView>> title(@PathVariable(value = "title") String title, Pageable page) {
        Page<Memo> memos = service.findByTitle(title, page);
        return new ResponseEntity<>(convert(memos.getContent()), HttpStatus.OK);
    }

    private MemoView convert(final Memo memo) {
        return MemoView.from(memo);
    }

    private List<MemoView> convert(final List<Memo> memos) {
        return memos.stream()
                .map(MemoView::from)
                .collect(Collectors.toList());
    }
}
