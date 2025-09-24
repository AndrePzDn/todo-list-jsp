package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteTodoCommand extends FrontCommand {
    private final TodoService todoService = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        HashMap<String, String> queryValues = getQueryValues();
        todoService.deleteTask(UUID.fromString(queryValues.get("id")));
        response.sendRedirect("/");
    }
}
