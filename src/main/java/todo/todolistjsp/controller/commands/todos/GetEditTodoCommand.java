package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.service.TodoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class GetEditTodoCommand extends FrontCommand {

    private final TodoService service = new TodoService();

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("action", "edit");

        HashMap<String, String> queryValues = getQueryValues();
        String id = queryValues.get("id");

        request.setAttribute("todo", service.findTaskById(UUID.fromString(id)));

        forward("todo-form.jsp");
    }
}
