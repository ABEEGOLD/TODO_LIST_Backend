package com.semicolon.africa.service;

import com.semicolon.africa.controllers.dtos.*;
import com.semicolon.africa.data.models.Task;
import src.main.java.com.semicolon.africa.controllers.dtos.UpdateTaskResponse;

import java.util.List;

public interface TaskServices {

   CreateTaskResponse createTask(CreateTaskRequest request);

   FetchTasksResponse fetchTask(FetchTaskRequest requestId);

  UpdateTaskResponse updateTask(UpdateTaskRequest update);

   DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTask);

   List<Task> findAllTasks();



}
