package com.semicolon.africa.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.message.Message;

@Entity
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    private String name;
    private String email;
    private String password;

    @Override
    public String toString() {
        return  "User{" + "userId=" + userId + ", name=" + name + ", password=" + password + '}';
    }
}
