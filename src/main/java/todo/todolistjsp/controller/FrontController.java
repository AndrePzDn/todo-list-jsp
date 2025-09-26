package todo.todolistjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import todo.todolistjsp.controller.commands.FrontCommand;
import todo.todolistjsp.model.HTTPMethod;
import todo.todolistjsp.model.HttpRequest;

import java.io.IOException;
import java.util.HashMap;

public class FrontController extends HttpServlet {
    protected final static HashMap<HttpRequest, FrontCommand> commands = new HashMap<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        String method = req.getParameter("_method") == null ? "GET" : req.getParameter("_method");
        String requestURI = req.getRequestURI();
        requestURI = normalizeURI(requestURI);

        HttpRequest httpRequest = new HttpRequest(HTTPMethod.valueOf(method.toUpperCase()), requestURI);
        return commands.get(httpRequest);
    }

    private String normalizeURI(String uri) {
        if (uri.endsWith("/")) {
            uri = uri.substring(0, uri.length() - 1);
        }

        return uri;
    }
}
