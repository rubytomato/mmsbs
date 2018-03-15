package com.example.application.controller;

import com.example.application.config.AppConfigure;
import com.example.application.vo.MemoView;
import com.example.domain.entity.Memo;
import com.example.domain.service.MemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MemoController.class, secure = false)
/*
//Solution 2
@Import(AppConfigure.class)
@TestPropertySource(properties = {
    "custom.application.key1=TEST_APP_VALUEA",
    "custom.application.key2=TEST_APP_VALUEB"
})
*/
public class MemoControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemoService service;
    @MockBean
    private AppConfigure config;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setup() {
        // solution 1
        Mockito.when(config.getKey1()).thenReturn("TEST_APP_VALUEA");
        Mockito.when(config.getKey2()).thenReturn("TEST_APP_VALUEB");
    }

    @Test
    public void test_id() throws Exception {
        Long id = 1L;
        LocalDateTime updated = LocalDateTime.of(2017, 9, 20, 13, 14, 15);
        Memo expected = Memo.builder().id(id).title("memo").description("memo description").done(false).updated(updated).build();
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(expected);

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/memo/id/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.title").value(expected.getTitle()))
                .andExpect(jsonPath("$.description").value(expected.getDescription()))
                .andExpect(jsonPath("$.completed").value(expected.getDone()))
                .andDo(print())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void test_id2() throws Exception {
        Long id = 1L;
        LocalDateTime updated = LocalDateTime.of(2017, 9, 20, 13, 14, 15);
        Memo expected = Memo.builder().id(id).title("memo").description("memo description").done(false).updated(updated).build();
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(expected);

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/memo/id2/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(print())
                .andReturn();

        MemoView actual = objectMapper.readValue(result.getResponse().getContentAsString(), MemoView.class);
        assertThat(actual)
                .extracting("title", "description", "done")
                .contains(expected.getTitle(), expected.getDescription(), expected.getDone());

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void test_title() throws Exception {

        Memo m1 = Memo.builder().id(1L).title("memo1 job").description("memo1 description").done(false).updated(LocalDateTime.now()).build();
        Memo m2 = Memo.builder().id(2L).title("memo2 job").description("memo2 description").done(false).updated(LocalDateTime.now()).build();
        Memo m3 = Memo.builder().id(3L).title("memo3 job").description("memo3 description").done(false).updated(LocalDateTime.now()).build();
        List<Memo> memos = Arrays.asList(m1, m2, m3);

        Page<Memo> expected = new PageImpl<>(memos);
        Mockito.when(service.findByTitle(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(expected);

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/memo/title/{title}", "job")
                .param("page","1")
                .param("size", "3")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(log())
                .andDo(print())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

}
