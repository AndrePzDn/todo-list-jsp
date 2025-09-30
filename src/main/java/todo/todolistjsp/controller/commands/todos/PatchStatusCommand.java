package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.mapper.TaskMapper;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

public class PatchStatusCommand extends FrontCommand {
    private TodoService todoService;

    public void init() {
        DataSource ds = DataSourceFactory.createDataSource();
        PostgresTaskRepository repository = new PostgresTaskRepository(ds, new TaskMapper());
        todoService = new TodoService(repository);
    }

    @Override
    public void process() throws ServletException, IOException {
        init();
        todoService.updateStatus(UUID.fromString(request.getParameter("id")), Status.COMPLETE);
        request.getSession().setAttribute("toastMessage", "Task status was updated");
        response.sendRedirect("/");
    }

}
