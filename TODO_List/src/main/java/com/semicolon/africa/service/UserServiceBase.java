package com.semicolon.africa.service;

import com.semicolon.africa.dtos.Request.ChangePasswordRequest;
import com.semicolon.africa.dtos.Request.LoginUserRequest;
import com.semicolon.africa.dtos.Request.LogoutUserRequest;
import com.semicolon.africa.dtos.Request.RegisterUserRequest;
import com.semicolon.africa.dtos.Response.ChangePasswordResponse;
import com.semicolon.africa.dtos.Response.LoginUserResponse;
import com.semicolon.africa.dtos.Response.LogoutUserResponse;
import com.semicolon.africa.dtos.Response.RegisterUserResponse;
import com.semicolon.africa.data.models.User;
import com.semicolon.africa.data.repositories.UserRepository;
import com.semicolon.africa.exceptions.LoginUserNotAvaiableException;
import com.semicolon.africa.exceptions.OldPasswordIncorrectException;
import com.semicolon.africa.exceptions.UserOldPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBase implements UserService {
   @Autowired
    private UserRepository userRepository;
   private User user;

   @Override
   public RegisterUserResponse registerUser(RegisterUserRequest userRequest){
       User user = new User();
       user.setUserId(userRequest.getUserId());
       user.setName(userRequest.getName());
       user.setUserPasswordHash(userRequest.getUserPasswordHash());
       userRepository.save(user);
       RegisterUserResponse registerResponse = new RegisterUserResponse();
       registerResponse.setMessage("Registered User Successful");
       return registerResponse;
   }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginRequest){
        User user = userRepository.findUserByUserId(loginRequest.getUserId());
        if(user == null){
            throw  new LoginUserNotAvaiableException("User not found");
        }
        userRepository.save(user);

        LoginUserResponse loginResponse = new LoginUserResponse();
        loginResponse.setMessage("Login Successful");
        return loginResponse;

    }

    @Override
    public LogoutUserResponse logoutUser(LogoutUserRequest logoutRequest){
        User user = new User();
        user.setUserId(logoutRequest.getUserId());
        user.setUserPasswordHash(logoutRequest.getUserPasswordHash());
        userRepository.save(user);
        LogoutUserResponse logoutResponse = new LogoutUserResponse();
        logoutResponse.setMessage("Logout Successful");
        return logoutResponse;

    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        if (request.getUserId() == null ||
                request.getOldPassword() == null ||
                request.getNewPassword() == null) {
            throw new IllegalArgumentException("User ID, old password, and new password must not be null");
        }

        User user = userRepository.findUserByUserId(request.getUserId());
        if (user == null) {
            throw new UserOldPasswordException("User not found");
        }
        String storedHashedPassword = user.getUserPasswordHash();
        String oldPasswordFromRequest = request.getOldPassword();

        if (storedHashedPassword == null || !BCrypt.checkpw(oldPasswordFromRequest, storedHashedPassword)) {
            throw new OldPasswordIncorrectException("Old password is incorrect");
        }


        String newHashedPassword = BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt());
        user.setUserPasswordHash(newHashedPassword);
        userRepository.save(user);

        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setMessage("Password changed successfully");
        return response;
    }





}

