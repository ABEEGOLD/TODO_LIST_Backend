package com.semicolon.africa.dtos.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTaskRequest {
    private Long taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean isComplete;

}
