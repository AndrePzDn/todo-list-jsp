<%@ page import="todo.todolistjsp.model.Task" %>
<%@ page import="todo.todolistjsp.model.Status" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        <%=request.getAttribute("action") == "edit" ? "Edit Task" : "Create Task"%>
    </title>
</head>
<style>
    <%@include file="../styles/todo-form.css" %>
</style>
<body>
<jsp:include page="common/header.jsp"/>
<section class="form-container">
    <%
        List<String> errors = (List<String>) request.getAttribute("errors");
        if (errors != null && !errors.isEmpty()) {
    %>
    <span>The follow errors were founded: </span>
    <%
        for (String error : errors) {
    %>
    <p><%=error%>
    </p>

    <form
            action="/Todolist/<%=request.getParameter("id") != null ? "edit?id="+ request.getParameter("id") : "new"%>"
            method="post"
    >
        <input type="submit" value="Try Again"/>
    </form>
    <%
        }
    } else {
    %>
    <h2>
        <%=request.getAttribute("action") == "edit" ? "Edit Task" : "Create Task"%>
    </h2>
    <% Task task = ((Task) request.getAttribute("todo")); %>
    <form
            action="<%="/Todolist/" + (task != null ? ("edit/?id=" + task.getId()) : "new")%>"
            method="post"
    >
        <p>
            <label for="title">Title</label>
            <input
                    type="text"
                    id="title"
                    name="title"
                    value="<%=task != null ? task.getTitle() : ""%>"
                    required
            />
        </p>
        <p>
            <label for="description">Description</label>
            <input
                    type="text"
                    id="description"
                    name="description"
                    required
                    value="<%=task != null ? task.getDescription() : ""%>"
            />
        </p>
        <p>
            <label for="status">Status</label>
            <select
                    id="status"
                    name="status"
                    class="input"
            >
                <% for (Status status : Status.values()) { %>
                <option
                        value="<%=status%>"
                        <%= (task != null && task.getStatus() == status) ? "selected" : ""%>
                >
                    <%=status.toString()%>
                </option>
                <% } %>
            </select>
        </p>
        <p>
            <label for="target-date">Target Date</label>
            <input
                    id="target-date"
                    type="date"
                    name="targetDate"
                    value="<%=task != null ? task.getTargetDate() : ""%>"
                    required
            />
        </p>
        <p>
            <label for="start-date">Start Date</label>
            <input
                    id="start-date"
                    type="date"
                    name="startDate"
                    value="<%=task != null ? task.getStartDate() : ""%>"
                    required
            />
        </p>
        <%
            if (task != null) {
        %>
        <label>
            <input hidden name="id" value="<%= task.getId() %>"/>
        </label>
        <%
            }
        %>
        <label>
            <input hidden name="_method" value="<%= task != null ? "PUT" : "POST"%>"/>
        </label>
        <button type="submit">Save</button>
    </form>
    <%
        }
    %>
</section>
</body>
</html>
