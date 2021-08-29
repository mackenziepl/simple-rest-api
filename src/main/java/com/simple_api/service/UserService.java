package com.simple_api.service;

import com.simple_api.controller.exception.ResourceNotFoundException;
import com.simple_api.domain.User;

public interface UserService {

    User getUser(final String login) throws ResourceNotFoundException;
}
