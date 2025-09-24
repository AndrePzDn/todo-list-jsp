package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.service.TodoService;

public class PostEditTodoCommand extends FrontCommand {
    private final TodoService service = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        HashMap<String, String> queryValues = getQueryValues();

        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        Status status = Status.valueOf(request.getParameter("status").toUpperCase());
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"), formatter);
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), formatter);
        UUID id = UUID.fromString(queryValues.get("id"));

        Task task = new Task(id, title, description, status, targetDate, startDate);
        service.editTask(id, task);
        response.sendRedirect("/");
    }
}
