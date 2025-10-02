import com.semicolon.africa.Main;
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
import com.semicolon.africa.service.TaskServiceBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@ExtendWith(MockitoExtension.class)
public class TaskServicesBaseTest {



        @Mock
        private TasksRepository tasksRepository;

        @InjectMocks
        private TaskServiceBase taskServicesBase;

        @Test
        public void testCreateTask() {
            CreateTaskRequest request = getCreateTaskRequest();

            Task savedTask = new Task();
            savedTask.setTitle(request.getTitle());
            savedTask.setDescription(request.getDescription());
            Mockito.when(tasksRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);

            CreateTaskResponse response = taskServicesBase.createTask(request);
            assertEquals("Created Task successful", response.getMessage());
        }

        public CreateTaskRequest getCreateTaskRequest() {
            CreateTaskRequest request = new CreateTaskRequest();
            request.setTitle("cooking");
            request.setDescription("cooking sauce");
            request.setDueDate(LocalDateTime.now());
            request.setComplete(true);
            return request;
        }

        @Test
        public void testFetchTask_success_and_failure() {
            Task task = new Task();
            task.setTaskId(1L);
            task.setTitle("school");
            task.setDescription("Attend classes");
            task.setComplete(false);

            FetchTaskRequest request = new FetchTaskRequest();
            request.setTitle("school");


            Mockito.when(tasksRepository.findTaskByTitle("school")).thenReturn(task);
            FetchTasksResponse response = taskServicesBase.fetchTask(request);
            assertNotNull(response);
            assertEquals("Fetching Task successful", response.getMessage());
            assertEquals("school", response.getTasks().getTitle());


            FetchTaskRequest missing = new FetchTaskRequest();
            missing.setTitle("nonexistent");
            Mockito.when(tasksRepository.findTaskByTitle("nonexistent")).thenReturn(null);

            TaskNotFoundException thrown = assertThrows(TaskNotFoundException.class, () -> {
                taskServicesBase.fetchTask(missing);
            });

            assertEquals("Task with title 'nonexistent' not found", thrown.getMessage());
        }

        @Test
        public void testUpdateTask() {
            UpdateTaskRequest request = getUpdateTaskRequest();

            Task existingTask = new Task();
            existingTask.setTitle(request.getTitle());
            existingTask.setDescription("old desc");
            existingTask.setComplete(false);

            Mockito.when(tasksRepository.findTaskByTitle(request.getTitle())).thenReturn(existingTask);
            Mockito.when(tasksRepository.save(Mockito.any(Task.class))).thenReturn(existingTask);

            UpdateTaskResponse response = taskServicesBase.updateTask(request);
            assertEquals("Task updated successfully", response.getMessage());
        }

        public UpdateTaskRequest getUpdateTaskRequest() {
            UpdateTaskRequest request = new UpdateTaskRequest();
            request.setTitle("cooking");
            request.setDescription("cooking sauce");
            request.setComplete(true);
            request.setDueDate(LocalDateTime.now());
            return request;
        }

        @Test
        public void testDeleteTask() {
            DeleteTaskRequest request = new DeleteTaskRequest();
            request.setTitle("cooking");

            Task taskToDelete = new Task();
            taskToDelete.setTitle("cooking");

            Mockito.when(tasksRepository.findTaskByTitle("cooking")).thenReturn(taskToDelete);

            DeleteTaskResponse response = taskServicesBase.deleteTask(request);
            assertEquals("Deleted Task successful", response.getMessage());
        }

        @Test
        public void testFindAllTasks() {
            Task task1 = new Task();
            task1.setTitle("cooking");

            Task task2 = new Task();
            task2.setTitle("studying");

            List<Task> taskList = List.of(task1, task2);
            Mockito.when(tasksRepository.findAll()).thenReturn(taskList);

            List<Task> result = taskServicesBase.findAllTasks();
            assertEquals(2, result.size());
            assertEquals("cooking", result.get(0).getTitle());
        }
    }


