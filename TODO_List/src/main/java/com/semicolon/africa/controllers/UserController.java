package com.semicolon.africa.controllers;

import com.semicolon.africa.dtos.Request.ChangePasswordRequest;
import com.semicolon.africa.dtos.Request.LoginUserRequest;
import com.semicolon.africa.dtos.Request.LogoutUserRequest;
import com.semicolon.africa.dtos.Request.RegisterUserRequest;
import com.semicolon.africa.dtos.Response.ChangePasswordResponse;
import com.semicolon.africa.dtos.Response.LoginUserResponse;
import com.semicolon.africa.dtos.Response.LogoutUserResponse;
import com.semicolon.africa.dtos.Response.RegisterUserResponse;
import com.semicolon.africa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        return ResponseEntity.ok(userService.loginUser(loginUserRequest));
    }
    @PostMapping("/logout")
    public ResponseEntity<LogoutUserResponse> logoutUser(@RequestBody LogoutUserRequest logoutUserRequest){
        return ResponseEntity.ok(userService.logoutUser(logoutUserRequest));
    }
    @PutMapping("/Password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePassword){
        return ResponseEntity.ok(userService.changePassword(changePassword));
    }
}
