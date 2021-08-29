package com.simple_api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple_api.entity.Counter;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Integer> {

    Optional<Counter> findByLogin(final String login);
}
