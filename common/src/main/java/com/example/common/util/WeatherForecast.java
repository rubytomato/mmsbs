package com.example.common.util;

import com.example.common.config.CommonConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@Slf4j
public class WeatherForecast {

    @Autowired
    private CommonConfigure config;

    public String getReportDayStringValue(LocalDate reportDay) {
        log.debug("getReportDayStringValue - reportDay:{}, config:{}", reportDay, config);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getDatePattern());
        return reportDay.format(formatter);
    }

    public String report(LocalDate reportDay) {
        log.debug("report - reportDay:{}", reportDay);
        String weather = "weather forecast : " + callForecastApi(reportDay);
        return weather;
    }

    String callForecastApi(LocalDate date) {
        // call External Weather API
        String apiResult = UUID.randomUUID().toString();
        String dateStr = date.toString();
        return apiResult + "-" + dateStr;
    }

}
