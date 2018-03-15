package com.example.application.controller;

import com.example.application.vo.MemoView;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoControllerJoinTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void test_id() {
        MemoView expected = MemoView.builder().title("memo shopping").description("memo1 description").done(false).build();
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);
        ResponseEntity<MemoView> actual = restTemplate.getForEntity("/memo/id/{id}", MemoView.class, params);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getHeaders().getContentType()).isEqualTo(contentType);
        assertThat(actual.getBody()).isEqualTo(expected);
    }

    @Test
    public void test_id2() {
        MemoView expected = MemoView.builder().title("memo shopping").description("memo1 description").done(false).build();
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);
        MemoView actual = restTemplate.getForObject("/memo/id2/{id}", MemoView.class, params);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test_title() {
        RequestEntity requestEntity = RequestEntity.get(URI.create("/memo/title/job?page=1&size=3&sort=id,desc")).build();
        ResponseEntity<List<MemoView>> actual = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<List<MemoView>>(){});

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getHeaders().getContentType()).isEqualTo(contentType);
        assertThat(actual.getBody())
                .extracting("title", "description", "done")
                .containsExactly(
                        Tuple.tuple("memo job", "memo4 description", false),
                        Tuple.tuple("memo job", "memo2 description", false)
                );
    }
}
