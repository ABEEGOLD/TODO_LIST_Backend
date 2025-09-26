package com.semicolon.africa.dtos.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    private Long UserId;
    private String name;
    private String userPasswordHash;

}

