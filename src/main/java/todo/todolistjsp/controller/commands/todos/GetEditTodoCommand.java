package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.mapper.TaskMapper;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

public class GetEditTodoCommand extends FrontCommand {

    private TodoService todoService;

    // @Inject
    // public GetEditTodoCommand(TodoService todoService) {
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
        request.setAttribute("action", "edit");

        HashMap<String, String> queryValues = getQueryValues();
        String id = queryValues.get("id");

        request.setAttribute("todo", todoService.findTaskById(UUID.fromString(id)));

        forward("todo-form.jsp");
    }
}
