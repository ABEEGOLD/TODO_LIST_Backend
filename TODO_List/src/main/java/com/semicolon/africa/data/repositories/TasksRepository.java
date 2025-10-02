package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.Task;
import com.semicolon.africa.dtos.Request.UpdateTaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

   Task findTaskByTitle(String title);

   Task readByComplete(boolean complete);


   Task findTaskByTaskId(Long taskId);
}
