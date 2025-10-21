import com.semicolon.africa.Main;
import com.semicolon.africa.dtos.Request.ChangePasswordRequest;
import com.semicolon.africa.dtos.Request.LoginUserRequest;
import com.semicolon.africa.dtos.Request.LogoutUserRequest;
import com.semicolon.africa.dtos.Request.RegisterUserRequest;
import com.semicolon.africa.dtos.Response.LoginUserResponse;
import com.semicolon.africa.dtos.Response.LogoutUserResponse;
import com.semicolon.africa.dtos.Response.RegisterUserResponse;
import com.semicolon.africa.data.models.User;
import com.semicolon.africa.data.repositories.UserRepository;
import com.semicolon.africa.exceptions.LoginUserNotAvailableException;
import com.semicolon.africa.exceptions.OldPasswordIncorrectException;
import com.semicolon.africa.service.UserServiceBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Main.class)

public class UserServiceBaseTest {



    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserServiceBase userServiceBase;

    @BeforeEach
    public void cleanDb() {
        userRepository.deleteAll();

        User user = new User();
        user.setName("REY");
        user.setPassword(passwordEncoder.encode("passwordEncode"));

        userRepository.save(user);
}


    @Test
    public void testThatUser_canRegister(){
        RegisterUserRequest register =  getRequest();
        RegisterUserResponse response = userServiceBase.registerUser(register);
        assertEquals("Registered User Successful", response.getMessage());


    }

    public RegisterUserRequest getRequest(){
        RegisterUserRequest request = new RegisterUserRequest();
//      request.setUserId(1L);
        request.setName("REY");
        request.setEmail("rey@gmail.com");
        request.setPassword("passwordEncode");
        return request;
    }
    @Test
    public void testCan_loginUser() {
        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("REY");
        request.setPassword("passwordEncode");
        LoginUserResponse response = userServiceBase.loginUser(request);
        assertNotNull(response);
        assertEquals("Login Successful", response.getMessage());

    }


    @Test
        public void testCan_loginUser_WithWrongPassword_ThrowsException() {

            LoginUserRequest request = new LoginUserRequest();
            request.setUsername("test_user");
            request.setPassword("wrong_password");


            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                userServiceBase.loginUser(request);
            });

            assertEquals("User not found", exception.getMessage());
        }

        @Test
        public void testCan_loginUser_WithNonExistentUser_ThrowsException() {

            LoginUserRequest request = new LoginUserRequest();
            request.setUsername("nou_ser");
            request.setPassword("any");


            LoginUserNotAvailableException exception = assertThrows(LoginUserNotAvailableException.class, () -> {
                userServiceBase.loginUser(request);
            });

            assertEquals("User not found", exception.getMessage());
        }


    @Test
    public void testCan_logoutUser(){
        User user = new User();
        userRepository.save(user);
        LogoutUserRequest out = new LogoutUserRequest();
        out.setUserPasswordHash("passwordEncode");
        LogoutUserResponse response = userServiceBase.logoutUser(out);
        assertEquals("Logout Successful", response.getMessage());

    }


    @Test
    public void testCan_changePassword() {
            User user = new User();
            user.setName("Mike");
            user.setEmail("mike@gmail.com");
            user.setPassword(BCrypt.hashpw("correctPassword", BCrypt.gensalt()));
            userRepository.save(user);

            ChangePasswordRequest request = new ChangePasswordRequest();
            request.setUserName("Mike");
            request.setOldPassword("wrongPassword");
            request.setNewPassword("NewPassword444");

        OldPasswordIncorrectException exception = assertThrows(OldPasswordIncorrectException.class, () -> {
            userServiceBase.changePassword(request);
        });


            assertEquals("Old password is incorrect", exception .getMessage());
        }



    }

