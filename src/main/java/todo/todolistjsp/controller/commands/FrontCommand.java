package todo.todolistjsp.controller.commands;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public abstract class FrontCommand {

    protected ServletContext servletContext;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        this.servletContext = servletContext;
        this.request = req;
        this.response = resp;
    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/views/%s", target);
        servletContext.getRequestDispatcher(target).forward(request, response);
    }

    protected HashMap<String, String> getQueryValues() {
        if (request.getQueryString() == null) {
            return new HashMap<>();
        }

        String[] queries = request.getQueryString().split("&");
        HashMap<String, String> queriesValues = new HashMap<>();
        for (String query : queries) {
            String[] queryValues = query.split("=");
            queriesValues.put(queryValues[0], queryValues[1]);
        }

        return queriesValues;
    }
}
