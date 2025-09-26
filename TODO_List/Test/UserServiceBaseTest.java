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
import com.semicolon.africa.exceptions.OldPasswordIncorrectException;
import com.semicolon.africa.service.UserServiceBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Main.class)

public class UserServiceBaseTest {

    @BeforeEach
    public void setUp() {
        userRepository.deleteById(3L);
    }


    @Autowired
    private UserServiceBase userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testCan_registerUser(){
        RegisterUserRequest register =  getRequest();
        RegisterUserResponse Response = userService.registerUser(register);
        assertEquals("Registered User Successful", Response.getMessage());


    }

    public RegisterUserRequest getRequest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserId(1L);
        request.setName("OFA");
        request.setUserPasswordHash("someHashedPassword");
        return request;
    }
    @Test
    public void testCan_loginUser() {
        LoginUserRequest request = new LoginUserRequest();
        request.setUserId(1L);
        request.setUserName("OFA");
        request.setUserPassword("someHashedPassword");
        LoginUserResponse Response = userService.loginUser(request);
        assertEquals("Login Successful", Response.getMessage());

    }
    @Test
    public void testCan_logoutUser(){
        User user = new User();
        userRepository.save(user);
        LogoutUserRequest out = new LogoutUserRequest();
        out.setUserId(user.getUserId());
        out.setUserPasswordHash("someHashedPassword");
        LogoutUserResponse response = userService.logoutUser(out);
        assertEquals("Logout Successful", response.getMessage());

    }


    @Test
    public void testCan_changePassword() {
            User user = new User();
            user.setName("Test User");
            user.setUserPasswordHash(BCrypt.hashpw("secret123", BCrypt.gensalt()));
            userRepository.save(user);

            ChangePasswordRequest request = new ChangePasswordRequest();
            request.setUserId(user.getUserId());
            request.setOldPassword("wrongPassword");
            request.setNewPassword("NewPassword444");

            Exception exception = assertThrows(OldPasswordIncorrectException.class, () -> {
                userService.changePassword(request);
            });

            assertEquals("Old password is incorrect", exception.getMessage());
        }



    }

