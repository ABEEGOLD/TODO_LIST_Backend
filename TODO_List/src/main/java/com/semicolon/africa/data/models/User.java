package com.semicolon.africa.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class User {
    @Id
    private Long userId;
    private String userName;
    private String userPassword;





}
