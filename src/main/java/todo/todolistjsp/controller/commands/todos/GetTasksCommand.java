package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.util.List;

public class GetTasksCommand extends FrontCommand {
    private final TodoService service = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        final int DEFAULT_SIZE = 10;
        final int DEFAULT_PAGE = 0;
        List<Task> taskList = service.findAllTasks(DEFAULT_SIZE, DEFAULT_PAGE);
        request.setAttribute("todos", taskList);
        forward("index.jsp");
    }
}
