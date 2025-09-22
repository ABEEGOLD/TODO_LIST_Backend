import com.semicolon.africa.Main;
import com.semicolon.africa.controllers.dtos.*;


import com.semicolon.africa.data.models.Task;
import com.semicolon.africa.service.TaskServicesBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = Main.class)

public class TaskServicesBaseTest {
    @Autowired
    private TaskServicesBase taskServicesBase;


    @Test
    public void testCreateTask() {
        CreateTaskRequest render = getCreateTaskRequest();
        CreateTaskResponse response = taskServicesBase.createTask(render);
        assertEquals("Created Task successful", response.getMessage());
    }


    public CreateTaskRequest getCreateTaskRequest() {
        CreateTaskRequest request = new CreateTaskRequest();

        request.setTitle("cooking");
        request.setDescription("cooking sauce");

        String var = "18-09-2025";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(var, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request.setDueDate(LocalDateTime.now());
        request.setComplete(true);
        return request;

    }

    @Test
    public void testFetchTask() {
        FetchTaskRequest render = new FetchTaskRequest();
        render.setId(2);
        FetchTasksResponse response = taskServicesBase.fetchTask(render);
        assertEquals("Fetching Task successful", response.getMessage());
    }

    @Test
    public void testUpdateTask() {
        UpdateTaskRequest render = getUpdateTaskRequest();
        src.main.java.com.semicolon.africa.controllers.dtos.UpdateTaskResponse response = taskServicesBase.updateTask(render);
        assertEquals("Task updated successfully", response.getMessage());


    }

    public UpdateTaskRequest getUpdateTaskRequest() {
        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setId(2);
        request.setTitle("cooking");
        request.setDescription("cooking sauce");
        request.setComplete(true);
        request.setDueDate(LocalDateTime.now());
        return request;
    }

@Test
    public void testDeleteTask() {
        DeleteTaskRequest render = new DeleteTaskRequest();
        render.setId(1);
        DeleteTaskResponse response = taskServicesBase.deleteTask(render);
        assertEquals("Deleted Task successful", response.getMessage());
    }

    @Test
    public void findAllTasks() {
        List<Task> tasks = taskServicesBase.findAllTasks();
        System.out.println(tasks);



    }
}
