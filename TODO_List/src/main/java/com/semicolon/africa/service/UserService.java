package com.semicolon.africa.service;

import com.semicolon.africa.dtos.Request.ChangePasswordRequest;
import com.semicolon.africa.dtos.Request.LoginUserRequest;
import com.semicolon.africa.dtos.Request.LogoutUserRequest;
import com.semicolon.africa.dtos.Request.RegisterUserRequest;
import com.semicolon.africa.dtos.Response.ChangePasswordResponse;
import com.semicolon.africa.dtos.Response.LoginUserResponse;
import com.semicolon.africa.dtos.Response.LogoutUserResponse;
import com.semicolon.africa.dtos.Response.RegisterUserResponse;

public interface UserService  {

    RegisterUserResponse registerUser(RegisterUserRequest userRequest);

    LoginUserResponse loginUser(LoginUserRequest loginRequest);

    LogoutUserResponse logoutUser(LogoutUserRequest logoutRequest);

    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);



}
