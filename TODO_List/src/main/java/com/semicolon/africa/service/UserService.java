package com.semicolon.africa.service;

import com.semicolon.africa.controllers.dtos.*;

public interface UserService  {

    RegisterUserResponse registerUser(RegisterUserRequest userRequest);

    LoginUserResponse loginUser(LoginUserRequest loginRequest);

    LogoutUserResponse logoutUser(LogoutUserRequest logoutRequest);

    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);

    DeleteUserAccountResponse deleteUserAccount(DeleteUserAccountRequest accountDeleteRequest);


}
