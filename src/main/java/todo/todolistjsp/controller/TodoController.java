package todo.todolistjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import todo.todolistjsp.controller.commands.todos.DeleteTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetAddTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetEditTodoCommand;
import todo.todolistjsp.controller.commands.todos.GetTasksCommand;
import todo.todolistjsp.controller.commands.todos.PostAddTodoCommand;
import todo.todolistjsp.controller.commands.todos.PostEditTodoCommand;
import todo.todolistjsp.model.HTTPMethod;
import todo.todolistjsp.model.HttpRequest;

@WebServlet(urlPatterns = "/Todolist/*")
public class TodoController extends FrontController {
    // @Inject
    // private Injector injector;

    @Override
    public void init() throws ServletException {
        commands.put(new HttpRequest(HTTPMethod.GET, "/Todolist"), new GetTasksCommand());
        commands.put(new HttpRequest(HTTPMethod.GET, "/Todolist/new"), new GetAddTodoCommand());
        commands.put(new HttpRequest(HTTPMethod.POST, "/Todolist/new"), new PostAddTodoCommand());
        commands.put(new HttpRequest(HTTPMethod.PUT, "/Todolist/edit"), new PostEditTodoCommand());
        commands.put(new HttpRequest(HTTPMethod.GET, "/Todolist/edit"), new GetEditTodoCommand());
        commands.put(new HttpRequest(HTTPMethod.DELETE, "/Todolist"), new DeleteTodoCommand());
    }
}
