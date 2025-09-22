package com.semicolon.africa.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class UpdateTaskRequest {
    private int id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean isComplete;
}
