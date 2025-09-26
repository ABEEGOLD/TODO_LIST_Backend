package com.semicolon.africa.dtos.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskResponse {

  private String message;

  public String getMessage() {
    return message;
  }

}
