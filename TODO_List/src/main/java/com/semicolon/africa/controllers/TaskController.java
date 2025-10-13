package com.semicolon.africa.controllers;

import com.semicolon.africa.data.models.Task;
import com.semicolon.africa.dtos.Request.*;
import com.semicolon.africa.dtos.Response.*;
import com.semicolon.africa.service.TaskServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskServiceBase taskServicesBase;

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.createTask(request));
    }

    @PostMapping("/fetch")
    public ResponseEntity<FetchTasksResponse> fetchTask(@RequestBody FetchTaskRequest request) {
        return ResponseEntity.ok(taskServicesBase.fetchTask(request));
    }



    @PutMapping("/update")
    public ResponseEntity<UpdateTaskResponse> updateTask(
            @RequestBody UpdateTaskRequest request) {

        UpdateTaskResponse response = taskServicesBase.updateTask(request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<DeleteTaskResponse> deleteTask(@RequestBody DeleteTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.deleteTask(request));
    }

    @GetMapping("/findAllTasks")
    public ResponseEntity<?> findAllTasks() {
        List<Task> tasks = taskServicesBase.findAllTasks();
        return ResponseEntity.ok(tasks);
    }

}
