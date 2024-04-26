package org.sopt.practice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.BlogService;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private BlogService blogService;

    @SpyBean
    private MemberService memberService;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper; /* Object <-> JSON String Array */

    @Nested
    class CreateBlog {
        @Test
        @DisplayName("blog create fail test")
        public void createBlogFail() throws Exception {
            // given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("test title", "test description"));

            // when
            mockMvc.perform(
                    post("/api/v1/blog")
                            .content(request)
                            .header("memberId", 2)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }
}
