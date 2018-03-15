package com.example.domain.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "custom.domain")
@Data
@Slf4j
public class DomainConfigure {
    private String key1;
    private String key2;

    @PostConstruct
    public void init() {
        log.info("DomainConfigure init : {}", this);
    }
}
