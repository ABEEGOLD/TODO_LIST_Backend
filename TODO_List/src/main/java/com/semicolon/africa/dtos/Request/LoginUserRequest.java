package com.semicolon.africa.dtos.Request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    @Column(unique = true)
    private String username;
    private String password;
}
