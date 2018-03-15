package com.example.application.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class MemoViewTests {

    private JacksonTester<MemoView> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void test_serialize() throws IOException {
        String expected = "{\"title\":\"memo\",\"description\":\"memo description\",\"completed\":false}";

        MemoView memoView = MemoView.builder().title("memo").description("memo description").done(false).build();
        JsonContent<MemoView> actual = json.write(memoView);

        assertThat(actual.getJson()).isEqualTo(expected);

        assertThat(actual).hasJsonPathStringValue("$.title");
        assertThat(actual).extractingJsonPathStringValue("$.title").isEqualTo("memo");
        assertThat(actual).hasJsonPathStringValue("$.description");
        assertThat(actual).extractingJsonPathStringValue("$.description").isEqualTo("memo description");
        assertThat(actual).hasJsonPathBooleanValue("$.completed");
        assertThat(actual).extractingJsonPathBooleanValue("$.completed").isEqualTo(false);
    }

    @Test
    public void test_deserialize() throws IOException {
        MemoView expected = MemoView.builder().title("memo").description("memo description").done(false).build();

        String content = "{\"title\":\"memo\",\"description\":\"memo description\",\"completed\":false}";
        ObjectContent<MemoView> actual = json.parse(content);
        actual.assertThat().isEqualTo(expected);
    }

}
