package com.semicolon.africa.dtos.Response;

import com.semicolon.africa.data.models.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTaskResponse {
    private String message;
    private Task task;
}
