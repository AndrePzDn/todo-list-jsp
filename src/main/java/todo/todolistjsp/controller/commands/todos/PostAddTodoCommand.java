package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PostAddTodoCommand extends FrontCommand {
    private final TodoService service = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        Status status = Status.valueOf(request.getParameter("status").toUpperCase());
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"), formatter);
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), formatter);

        Task task = new Task(UUID.randomUUID(), title, description, status, targetDate, startDate);
        service.saveTask(task);
        response.sendRedirect("/");
    }
}
