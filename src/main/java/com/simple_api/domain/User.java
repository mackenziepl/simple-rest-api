package com.simple_api.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private String name;
    private Type type;
    private String avatar_url;
    private Date created_at;
    private double calculations;
}
