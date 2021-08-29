package com.simple_api;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.simple_api.controller.exception.ResourceNotFoundException;
import com.simple_api.domain.Type;
import com.simple_api.domain.User;
import com.simple_api.service.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SimpleapiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest {

    private static final String URL = "/api/users/{login}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnUserDataWhenStatusIsOk() throws Exception {
        //Given
        User user = User.builder()
                .id(1)
                .login("octocat")
                .name("The Octoca")
                .type(Type.User)
                .avatar_url("https://avatars.githubusercontent.com/u/583231?v=4")
                .created_at(new Date())
                .calculations(0.015197568389057751)
                .build();

        //When //Then
        when(userService.getUser(anyString())).thenReturn(user);

        String existingUserLogin = "octocat";

        mvc.perform(get(URL, existingUserLogin)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", anyNumberMatcher()))
                .andExpect(jsonPath("$.login", anyStringMatcher()))
                .andExpect(jsonPath("$.name", anyStringMatcher()))
                .andExpect(jsonPath("$.type", anyStringMatcher()))
                .andExpect(jsonPath("$.avatar_url", anyStringMatcher()))
                .andExpect(jsonPath("$.created_at", anyStringMatcher()))
                .andExpect(jsonPath("$.calculations", anyNumberMatcher()));
    }

    @Test
    public void shouldReturn404WhenGetBadUserLogin() throws Exception {
        //Given//When//Then
        String badUserLogin = "badUserLogin";
        when(userService.getUser(anyString())).thenThrow(new ResourceNotFoundException("Not found user with login:" + badUserLogin));

        mvc.perform(get(URL, badUserLogin)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static Matcher<Number> anyNumberMatcher() {
        return CoreMatchers.any(Number.class);
    }

    private static Matcher<String> anyStringMatcher() {
        return CoreMatchers.any(String.class);
    }
}
