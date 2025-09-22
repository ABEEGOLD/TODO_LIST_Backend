package com.semicolon.africa.data.repositories;

import com.semicolon.africa.controllers.dtos.FetchTaskRequest;
import com.semicolon.africa.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

   Task findTaskByTaskId(int id);


   Task readByComplete(boolean complete);

   //List<Task> findAll();
}
