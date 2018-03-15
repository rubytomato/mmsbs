package com.example.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "custom.common")
@Data
@Slf4j
public class CommonConfigure {
    private String key1;
    private String key2;
    private String datePattern;

    @PostConstruct
    public void init() {
        log.info("CommonConfigure init : {}", this);
    }
}
