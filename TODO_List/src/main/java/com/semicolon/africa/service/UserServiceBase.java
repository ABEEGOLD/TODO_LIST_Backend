package com.semicolon.africa.service;

import com.semicolon.africa.controllers.dtos.*;
import com.semicolon.africa.data.models.User;
import com.semicolon.africa.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBase implements UserService {
   @Autowired
    private UserRepository userRepository;
   @Override
   public RegisterUserResponse registerUser(RegisterUserRequest userRequest){
       return null;

    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginRequest){
       return null;
    }

    @Override
    public LogoutUserResponse logoutUser(LogoutUserRequest logoutRequest){
       return null;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest){
       return null;
    }

    @Override
    public DeleteUserAccountResponse deleteUserAccount(DeleteUserAccountRequest accountDeleteRequest){
       return null;
    }


}
