package com.semicolon.africa.dtos.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FindAllTaskRequest {
//    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;
}
