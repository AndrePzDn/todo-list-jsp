package todo.todolistjsp.controller.commands.todos;

import jakarta.servlet.ServletException;
import todo.todolistjsp.controller.commands.FrontCommand;

import java.io.IOException;

public class GetAddTodoCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("todo-form.jsp");
    }
}
