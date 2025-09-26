package todo.todolistjsp.controller.commands;

import java.io.IOException;

import jakarta.servlet.ServletException;

public class NotFoundCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        forward("not-found.jsp");
    }
}
