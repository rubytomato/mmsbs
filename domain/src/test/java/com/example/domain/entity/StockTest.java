package com.example.domain.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class StockTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void test() throws Exception {
        String origin = "{\"color\": \"blue\", \"shape\": \"triangle\", \"status\": \"not started\"}";
        Stock.Notes expected = Stock.Notes.builder().color("blue").shape("triangle").status("not started").build();

        Stock.Notes actual = objectMapper.readValue(origin, Stock.Notes.class);
        //System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }

}
