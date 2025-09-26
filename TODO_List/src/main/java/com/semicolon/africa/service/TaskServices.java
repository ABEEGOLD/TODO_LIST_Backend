package com.semicolon.africa.service;

import com.semicolon.africa.dtos.Request.CreateTaskRequest;
import com.semicolon.africa.dtos.Request.DeleteTaskRequest;
import com.semicolon.africa.dtos.Request.FetchTaskRequest;
import com.semicolon.africa.dtos.Request.UpdateTaskRequest;
import com.semicolon.africa.dtos.Response.CreateTaskResponse;
import com.semicolon.africa.dtos.Response.DeleteTaskResponse;
import com.semicolon.africa.dtos.Response.FetchTasksResponse;
import com.semicolon.africa.dtos.Response.UpdateTaskResponse;
import com.semicolon.africa.data.models.Task;

import java.util.List;

public interface TaskServices {

   CreateTaskResponse createTask(CreateTaskRequest request);

   FetchTasksResponse fetchTask(FetchTaskRequest requestId);

    UpdateTaskResponse updateTask(UpdateTaskRequest update);

   DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTask);

   List<Task> findAllTasks();



}
