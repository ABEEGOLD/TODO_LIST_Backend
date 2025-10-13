package com.semicolon.africa.controllers;

import com.semicolon.africa.dtos.Request.ChangePasswordRequest;
import com.semicolon.africa.dtos.Request.LoginUserRequest;
import com.semicolon.africa.dtos.Request.LogoutUserRequest;
import com.semicolon.africa.dtos.Request.RegisterUserRequest;
import com.semicolon.africa.dtos.Response.LogoutUserResponse;
import com.semicolon.africa.dtos.Response.RegisterUserResponse;
import com.semicolon.africa.service.UserServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceBase userService;

    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser( @RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest) {

        try{
        return  ResponseEntity.ok(userService.loginUser(loginUserRequest));
        }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<LogoutUserResponse> logoutUser(@RequestBody LogoutUserRequest logoutUserRequest){
        return ResponseEntity.ok(userService.logoutUser(logoutUserRequest));
    }
    @PutMapping("/Password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePassword){
        try{
            return ResponseEntity.ok(userService.changePassword(changePassword));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
