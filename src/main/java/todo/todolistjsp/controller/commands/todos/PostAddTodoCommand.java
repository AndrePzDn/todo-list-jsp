package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

public class PostAddTodoCommand extends FrontCommand {

    private TodoService todoService;

    // @Inject
    // public PostAddTodoCommand(TodoService todoService) {
    //     this.todoService = todoService;
    // }

    public void init() {
        DataSource ds = DataSourceFactory.createDataSource();
        PostgresTaskRepository repository = new PostgresTaskRepository(ds);
        todoService = new TodoService(repository);
    }

    @Override
    public void process() throws ServletException, IOException {
        init();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        Status status = Status.valueOf(request.getParameter("status").toUpperCase());
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"), formatter);
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), formatter);

        Task task = new Task(UUID.randomUUID(), title, description, status, targetDate, startDate);
        todoService.saveTask(task);
        response.sendRedirect("/");
    }
}
