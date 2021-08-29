package com.simple_api.mapper;

import com.simple_api.domain.User;
import com.simple_api.domain.dto.UserDto;

public class UserMapper {

    public static User mapToUser(UserDto in) {
        return User.builder()
                .id(in.getId())
                .login(in.getLogin())
                .name(in.getName())
                .type(in.getType())
                .avatar_url(in.getAvatar_url())
                .created_at(in.getCreated_at())
                .calculations(in.getCalculationsResult())
                .build();
    }
}
