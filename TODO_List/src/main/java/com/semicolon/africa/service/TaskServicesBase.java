package com.semicolon.africa.service;

import com.semicolon.africa.controllers.dtos.*;
import com.semicolon.africa.data.models.Task;
import com.semicolon.africa.data.repositories.TasksRepository;
import com.semicolon.africa.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.main.java.com.semicolon.africa.controllers.dtos.UpdateTaskResponse;

import java.util.List;


@Service
public class TaskServicesBase implements com.semicolon.africa.service.TaskServices {
    @Autowired
    private TasksRepository tasksRepository;
    @Override
    public com.semicolon.africa.controllers.dtos.CreateTaskResponse createTask(com.semicolon.africa.controllers.dtos.CreateTaskRequest request){
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setTaskId(request.getTaskId());
        task.setDueDate(request.getDueDate());
        task.setComplete(request.isComplete());
        tasksRepository.save(task);
        com.semicolon.africa.controllers.dtos.CreateTaskResponse create = new com.semicolon.africa.controllers.dtos.CreateTaskResponse();
        create.setMessage("Created Task successful");
       return create;



    }



    @Override
    public FetchTasksResponse fetchTask(com.semicolon.africa.controllers.dtos.FetchTaskRequest requestId) {
      Task  tasks = tasksRepository.findTaskByTaskId(requestId.getId());
      if(tasks == null) {
          throw new TaskNotFoundException("Tasks not found");
      }
        FetchTasksResponse fetch = new FetchTasksResponse();
      fetch.setMessage("Fetching Task successful");
      fetch.setTasks(tasks);
      return fetch;




    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest update) {
        Task task = tasksRepository.findTaskByTaskId(update.getId());
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + update.getId() + " not found");
        }

        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setDueDate(update.getDueDate());
        task.setComplete(update.isComplete());
        tasksRepository.save(task);

        src.main.java.com.semicolon.africa.controllers.dtos.UpdateTaskResponse updated = new src.main.java.com.semicolon.africa.controllers.dtos.UpdateTaskResponse();
        updated.setMessage("Task updated successfully");
        updated.setTask(task);
        return updated;
    }


    @Override
    public com.semicolon.africa.controllers.dtos.DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTask) {
        Task task = tasksRepository.findTaskByTaskId(deleteTask.getId());
    if(task == null) {
        throw new TaskNotFoundException("Task not found");
    }
        tasksRepository.delete(task);

        DeleteTaskResponse delete = new DeleteTaskResponse();
        delete.setMessage("Deleted Task successful");
        delete.setTask(task);
        return delete;
    }

    public List<Task> findAllTasks() {
        List<Task>  tasks = tasksRepository.findAll();
        return tasks;









    }
}

