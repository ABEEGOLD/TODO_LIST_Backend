package com.semicolon.africa.dtos.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {

    private String userName;
    private String userPassword;
}
