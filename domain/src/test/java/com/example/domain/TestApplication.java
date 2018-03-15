package com.example.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.example.domain",
    "com.example.common"
})
public class TestApplication {
    public static void main(String... args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
