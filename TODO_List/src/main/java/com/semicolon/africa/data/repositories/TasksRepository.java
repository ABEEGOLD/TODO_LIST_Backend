package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

   Task findTaskByTaskId(Long taskId);


   Task readByComplete(boolean complete);





   //List<Task> findAll();
}
