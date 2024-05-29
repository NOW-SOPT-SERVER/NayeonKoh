package org.sopt.karrot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sopt.karrot.exception.GlobalExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class MockMVCUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();
    protected MockMvc mockMvc;

    protected MockMvc mockController(Object controller) {
        return MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    protected ResultActions whenPost(String url, Object body) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(body))
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(request);
    }

    protected ResultActions whenGet(String url) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(request);
    }

    protected ResultActions whenGet(String url, Long pathVariable) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url, pathVariable)
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(request);
    }

    protected String toJson(Object request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private <T> T fromJson(String content, Class<T> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
