package com.simple_api.service.impl;

import org.springframework.stereotype.Service;

import com.simple_api.entity.Counter;
import com.simple_api.repository.CounterRepository;
import com.simple_api.service.CounterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterServiceImpl implements CounterService {

    private final CounterRepository repository;

    @Override
    public void addCounter(Counter counter) {
        if(isCounterExists(counter.getLogin())) {
            counter = incrementRequestCount(counter.getLogin());
        }
        repository.save(counter);
    }

    private boolean isCounterExists(final String login) {
        return repository.findByLogin(login).isPresent();
    }

    private Counter incrementRequestCount(final String login) {
        return repository.findByLogin(login)
                .map(counter -> {
                    return Counter.builder()
                            .id(counter.getId())
                            .login(counter.getLogin())
                            .requestCount(counter.getRequestCount() + 1)
                            .build();
                })
                .get();
    }
}
