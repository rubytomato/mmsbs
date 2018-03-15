package com.example.common.constant;

public enum Weather {
    sunny("晴"),
    cloudy("曇"),
    raining("雨"),
    snowy("雪"),
    hazy("霧");

    Weather(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
