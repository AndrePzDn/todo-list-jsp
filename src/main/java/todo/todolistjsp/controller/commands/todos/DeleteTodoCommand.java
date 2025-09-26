package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.sql.DataSource;

public class DeleteTodoCommand extends FrontCommand {
    private TodoService todoService;

    // @Inject
    // public DeleteTodoCommand(TodoService todoService) {
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
        HashMap<String, String> queryValues = getQueryValues();
        todoService.deleteTask(UUID.fromString(queryValues.get("id")));
        response.sendRedirect("/");
    }
}
