package com.example.application.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "custom.application")
@Data
@Slf4j
public class AppConfigure {
    private String key1;
    private String key2;
    //private String key3;

    @PostConstruct
    public void init() {
        log.info("AppConfigure init : {}", this);
    }
}
