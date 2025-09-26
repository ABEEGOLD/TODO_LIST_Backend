package com.semicolon.africa.controllers;

import com.semicolon.africa.dtos.Request.*;
import com.semicolon.africa.dtos.Response.*;
import com.semicolon.africa.service.TaskServicesBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Tasks")
public class TaskController {

    @Autowired
    private TaskServicesBase taskServicesBase;

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.createTask(request));
    }

    @GetMapping("/fetch")
    public ResponseEntity<FetchTasksResponse> fetchTask(@RequestBody FetchTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.fetchTask(request));

    }

    @PutMapping("/update")
    public ResponseEntity<UpdateTaskResponse> updateTask(@RequestBody UpdateTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.updateTask(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteTaskResponse> deleteTask(@RequestBody DeleteTaskRequest request){
        return ResponseEntity.ok(taskServicesBase.deleteTask(request));
    }

//    @GetMapping
//    public ResponseEntity<FindAllTaskResponse> findAllTasks(FindAllTaskRequest request){
//        return ResponseEntity.ok(taskServicesBase.findAllTasks());
//    }
}
