package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetTasksCommand extends FrontCommand {
    private final TodoService service = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        int DEFAULT_SIZE = 10;
        int DEFAULT_PAGE = 1;
        HashMap<String, String> queryValues = getQueryValues();

        int pageSize = queryValues.get("size") != null
                ? (Integer.parseInt(queryValues.get("size")))
                : DEFAULT_SIZE;
        int page = queryValues.get("page") != null
                ? (Integer.parseInt(queryValues.get("page")))
                : DEFAULT_PAGE;

        List<Task> taskList = service.findAllTaskPaginated(page, pageSize);

        int totalPages = (service.findAllTasks().size() + pageSize - 1) / pageSize;
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("todos", taskList);
        forward("index.jsp");
    }
}
