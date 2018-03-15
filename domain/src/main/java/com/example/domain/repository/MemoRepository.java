package com.example.domain.repository;

import com.example.domain.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    Page<Memo> findByTitleLike(String title, Pageable page);
}
