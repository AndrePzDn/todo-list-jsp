package todo.todolistjsp.controller;

import jakarta.servlet.annotation.WebServlet;
import todo.todolistjsp.controller.commands.todos.GetTasksCommand;

@WebServlet(urlPatterns = {"/Todolist/*"})
public class TodoController extends FrontController {
    static {
        commands.put("GET_/Todolist", new GetTasksCommand());
    }
}
