package com.example.domain.service;

import com.example.domain.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MemoService {
    Memo findById(Long id);
    Page<Memo> findByTitle(String title, Pageable page);
    Memo registerWeatherMemo(LocalDate date);
}
