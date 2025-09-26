package todo.todolistjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import todo.todolistjsp.controller.commands.todos.DeleteTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetAddTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetEditTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetTasksCommand;
import todo.todolistjsp.controller.commands.todos.PostAddTodoCommand;
import todo.todolistjsp.controller.commands.todos.PostEditTodoCommand;

@WebServlet(urlPatterns = "/Todolist/*")
public class TodoController extends FrontController {
    // @Inject
    // private Injector injector;

    @Override
    public void init() throws ServletException {
        commands.put("GET_/Todolist", new GetTasksCommand());
        commands.put("GET_/Todolist/new", new GetAddTodoCommand());
        commands.put("POST_/Todolist/new", new PostAddTodoCommand());
        commands.put("PUT_/Todolist/edit", new PostEditTodoCommand());
        commands.put("GET_/Todolist/edit", new GetEditTodoCommand());
        commands.put("DELETE_/Todolist", new DeleteTodoCommand());
    }
}
