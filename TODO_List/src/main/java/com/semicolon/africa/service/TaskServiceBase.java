package com.semicolon.africa.service;

import com.semicolon.africa.data.models.Task;
import com.semicolon.africa.data.repositories.TasksRepository;
import com.semicolon.africa.dtos.Request.CreateTaskRequest;
import com.semicolon.africa.dtos.Request.DeleteTaskRequest;
import com.semicolon.africa.dtos.Request.FetchTaskRequest;
import com.semicolon.africa.dtos.Request.UpdateTaskRequest;
import com.semicolon.africa.dtos.Response.CreateTaskResponse;
import com.semicolon.africa.dtos.Response.DeleteTaskResponse;
import com.semicolon.africa.dtos.Response.FetchTasksResponse;
import com.semicolon.africa.dtos.Response.UpdateTaskResponse;
import com.semicolon.africa.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceBase implements TaskServices{
@Autowired
private TasksRepository tasksRepository;

@Override
public CreateTaskResponse createTask(CreateTaskRequest request){
    Task task = new Task();
    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
//  task.setTaskId(request.getTaskId());
    task.setDueDate(request.getDueDate());
    task.setComplete(request.isComplete());
    tasksRepository.save(task);
    CreateTaskResponse create = new CreateTaskResponse();
    create.setMessage("Created Task successful");
    return create;



        }


@Override
public FetchTasksResponse fetchTask(FetchTaskRequest request) {
    Task task = tasksRepository.findTaskByTitle(request.getTitle());
    if (task == null) {
        throw new TaskNotFoundException("Task with title '" + request.getTitle() + "' not found");
    }

    FetchTasksResponse response = new FetchTasksResponse();
    response.setMessage("Fetching Task successful");
    response.setTasks(task);
    return response;
}







@Override
public UpdateTaskResponse updateTask(UpdateTaskRequest update) {
    Task task = tasksRepository.findTaskByTitle(update.getTitle());
    if (task == null) {
        throw new TaskNotFoundException("Task with ID " + update.getTitle() + " not found");
    }

    task.setTitle(update.getTitle());
    task.setDescription(update.getDescription());
    task.setDueDate(update.getDueDate());
    task.setComplete(update.isComplete());
    tasksRepository.save(task);

    UpdateTaskResponse updated = new UpdateTaskResponse();
    updated.setMessage("Task updated successfully");
    updated.setTask(task);
    return updated;
    }


@Override
public DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTask) {
    Task task = tasksRepository.findTaskByTitle(deleteTask.getTitle());
    if(task == null) {
        throw new TaskNotFoundException("Task not found");
    }
    tasksRepository.delete(task);

    DeleteTaskResponse delete = new DeleteTaskResponse();
    delete.setMessage("Deleted Task successful");
    delete.setTask(task);
    return delete;
    }

@Override
public List<Task> findAllTasks() {
    List<Task> tasks = tasksRepository.findAll();
    return tasks;

}



}




