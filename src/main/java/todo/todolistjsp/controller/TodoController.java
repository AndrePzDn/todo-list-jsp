package todo.todolistjsp.controller;

import jakarta.servlet.annotation.WebServlet;
import todo.todolistjsp.controller.commands.todos.GetAddTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetTasksCommand;
import todo.todolistjsp.controller.commands.todos.PostAddTodoCommand;

@WebServlet(urlPatterns = {"/Todolist/*"})
public class TodoController extends FrontController {
    static {
        commands.put("GET_/Todolist", new GetTasksCommand());
        commands.put("GET_/Todolist/new", new GetAddTodoCommand());
        commands.put("POST_/Todolist/new", new PostAddTodoCommand());
    }
}
