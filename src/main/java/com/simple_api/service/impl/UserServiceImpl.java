package com.simple_api.service.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.simple_api.controller.exception.ResourceNotFoundException;
import com.simple_api.domain.User;
import com.simple_api.domain.dto.UserDto;
import com.simple_api.entity.Counter;
import com.simple_api.mapper.UserMapper;
import com.simple_api.service.CounterService;
import com.simple_api.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String URL = "https://api.github.com/users/";

    private final RestTemplate restTemplate;
    private final CounterService counterService;

    @Override
    public User getUser(final String login) {
        User user = UserMapper.mapToUser(getUserBody(login));

        addCounter(user);
        log.info("Added user with login: " + login);

        return user;
    }

    private void addCounter(User user) {
        counterService.addCounter(Counter.builder()
                .login(user.getLogin())
                .requestCount(1)
                .build());
    }

    private UserDto getUserBody(final String login) {
        String resourceUrl = URL + login;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<UserDto> entity = new HttpEntity<>(headers);
        return getUserDto(login, resourceUrl, entity);
    }

    private UserDto getUserDto(final String login, final String resourceUrl, final HttpEntity<UserDto> entity) {
        try {
            return restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, UserDto.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException("Not found user with login: " + login);
        }
    }
}
