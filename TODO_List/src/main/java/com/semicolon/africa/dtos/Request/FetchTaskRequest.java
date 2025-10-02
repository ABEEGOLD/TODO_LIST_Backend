package com.semicolon.africa.dtos.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FetchTaskRequest {
    private String title;
    private String description;

}
