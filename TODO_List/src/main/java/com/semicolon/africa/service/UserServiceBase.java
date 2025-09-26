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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBase implements UserService {
   @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  //  private User user;

   @Override
   public RegisterUserResponse registerUser(RegisterUserRequest userRequest){
       User user = new User();
       user.setUserId(userRequest.getUserId());
       user.setName(userRequest.getName());

       String hashedPassword = passwordEncoder.encode(userRequest.getUserPasswordHash());
       user.setUserPasswordHash(hashedPassword);

       userRepository.save(user);
       RegisterUserResponse registerResponse = new RegisterUserResponse();
       registerResponse.setMessage("Registered User Successful");
       return registerResponse;
   }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginRequest){
//        System.out.println("This is the request data"+ loginRequest);
        User user = userRepository.findUserByName(loginRequest.getUserName());
//        System.out.println("This is the user"+user.toString());
        if(user == null){
            throw  new LoginUserNotAvaiableException("User not found");
        }

        boolean passwordMatches = passwordEncoder.matches(
                loginRequest.getUserPassword(),
                user.getUserPasswordHash()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }


//        System.out.println("This is the password "+passwordMatches);
        LoginUserResponse loginResponse = new LoginUserResponse();
        loginResponse.setMessage("Login Successful");
        return loginResponse;

    }

    @Override
    public LogoutUserResponse logoutUser(LogoutUserRequest logoutRequest){
        User user = new User();
        user.setUserPasswordHash(logoutRequest.getUserPasswordHash());
        LogoutUserResponse logoutResponse = new LogoutUserResponse();
        logoutResponse.setMessage("Logout Successful");
        return logoutResponse;

    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
       if(request.getOldPassword() == null ||
                request.getNewPassword() == null) {
            throw new IllegalArgumentException("old password, and new password must not be null");
        }

        User user = userRepository.findUserByName(request.getUserName());
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

        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setMessage("Password changed successfully");
        return response;
    }





}

