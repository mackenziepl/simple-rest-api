package com.simple_api.domain.dto;

import java.util.Date;

import com.simple_api.domain.Type;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String login;
    private String name;
    private Type type;
    private String avatar_url;
    private Date created_at;
    private double calculations;
    private int followers;
    private int public_repos;

    public double getCalculationsResult() {
        return (double) 6 / this.followers * (2 + this.public_repos);
    }
}
