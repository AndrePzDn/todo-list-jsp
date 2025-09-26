package todo.todolistjsp.controller.commands.todos;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.repositories.concretes.PostgresTaskRepository;
import todo.todolistjsp.service.DataSourceFactory;
import todo.todolistjsp.service.TodoService;

public class GetTasksCommand extends FrontCommand {
    private TodoService todoService;

    // @Inject
    // public GetTasksCommand(TodoService todoService) {
    // this.todoService = todoService;
    // }

    public void init() {
        DataSource ds = DataSourceFactory.createDataSource();
        PostgresTaskRepository repository = new PostgresTaskRepository(ds);
        todoService = new TodoService(repository);
    }

    @Override
    public void process() throws ServletException, IOException {
        init();
        int DEFAULT_SIZE = 10;
        int DEFAULT_PAGE = 1;
        HashMap<String, String> queryValues = getQueryValues();

        int pageSize = queryValues.get("size") != null
                ? (Integer.parseInt(queryValues.get("size")))
                : DEFAULT_SIZE;
        int page = queryValues.get("page") != null
                ? (Integer.parseInt(queryValues.get("page")))
                : DEFAULT_PAGE;

        List<Task> taskList = todoService.findAllTaskPaginated(page, pageSize);

        int totalPages = (todoService.findAllTasks().size() + pageSize - 1) / pageSize;
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("todos", taskList);
        forward("index.jsp");
    }
}
