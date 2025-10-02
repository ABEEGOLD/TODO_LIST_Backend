package com.semicolon.africa.dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTaskRequest {
//    private Long taskId;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dueDate;
    private boolean isComplete;

}
