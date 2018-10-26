package com.thoughtworks.nho.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.repository.UserRepository;
import com.thoughtworks.nho.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    @BeforeEach
    void setup() {
        super.setup();
        userService.create(User.builder().name("future_star").password("123").build());
    }

    @AfterEach
    void teardown(){
        userRepository.deleteAllInBatch();
    }


    @Test
    void should_create_user() throws Exception {
        loginWithUser("future_star");
        User user = User.builder().name("new_future_star").password("123456").build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username").value("new_future_star"));
    }

    @Test
    void should_find_user() throws Exception {
        loginWithUser("future_star");
        mockMvc.perform(get("/api/users/future_star"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username").value("future_star"));
    }

}