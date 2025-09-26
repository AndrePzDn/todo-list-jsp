package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.dto.TaskUpdateDto;
import todo.todolistjsp.mapper.TaskMapper;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

public class PostEditTodoCommand extends FrontCommand {

    private TodoService todoService;

    // @Inject
    // public PostEditTodoCommand(TodoService todoService) {
    // this.todoService = todoService;
    // }

    public void init() {
        DataSource ds = DataSourceFactory.createDataSource();
        PostgresTaskRepository repository = new PostgresTaskRepository(ds, new TaskMapper());
        todoService = new TodoService(repository);
    }

    @Override
    public void process() throws ServletException, IOException {
        init();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        HashMap<String, String> queryValues = getQueryValues();

        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        Status status = Status.valueOf(request.getParameter("status").toUpperCase());
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"), formatter);
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), formatter);
        UUID id = UUID.fromString(queryValues.get("id"));

        TaskUpdateDto task = new TaskUpdateDto(title, description, status, targetDate, startDate);
        List<String> errors = todoService.editTask(id, task);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("todo", task);
            forward("todo-form.jsp");
            return;
        }

        response.sendRedirect("/");
    }
}
