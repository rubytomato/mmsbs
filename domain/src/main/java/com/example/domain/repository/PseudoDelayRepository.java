package com.example.domain.repository;

import com.example.domain.entity.PseudoDelay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PseudoDelayRepository extends JpaRepository<PseudoDelay, String> {
}
