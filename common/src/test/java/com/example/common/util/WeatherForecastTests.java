package com.example.common.util;

import com.example.common.config.CommonConfigure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherForecastTests {

    @Spy
    @InjectMocks
    private WeatherForecast sut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        CommonConfigure config = new CommonConfigure();
        config.setKey1("test_common_e");
        config.setKey2("test_common_f");
        config.setDatePattern("yyyy/MM/dd");
        Whitebox.setInternalState(sut, "config", config);
    }

    @Test
    public void test_getReportDayStringValue() {
        LocalDate date = LocalDate.of(2017, 9, 20);
        String actual = sut.getReportDayStringValue(date);
        assertThat(actual).isEqualTo("2017/09/20");
    }

    @Test
    public void test_report() {
        LocalDate date = LocalDate.of(2017, 9, 20);

        Mockito.when(sut.callForecastApi(date)).thenReturn("test-test-test");

        String actual = sut.report(date);
        assertThat(actual).isEqualTo("weather forecast : test-test-test");
    }

}
