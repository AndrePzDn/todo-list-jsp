<%@ page import="java.util.List" %>
<%@ page import="todo.todolistjsp.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body {
        padding: 0 0;
        margin: 0 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 100%;
    }

    table {
        width: 80%;
        justify-content: center;
        align-content: center;
    }

    .btn {
        padding: 0.5rem 1.5rem;
        border-radius: 0.75rem;
        background-color: oklch(54.6% 0.245 262.881);
        color: white;
    }
</style>
<body>
<jsp:include page="common/header.jsp"/>
<h1>List of Todos</h1>
<hr>
<form action="/Todolist/new" method="post">
    <input type="submit" value="Add" class="btn"/>
</form>
<table border="1" cellpadding="5">
    <tr>
        <td>Title</td>
        <td>Description</td>
        <td>Status</td>
        <td>Target Date</td>
        <td>Initial Date</td>
        <td>Actions</td>
    </tr>
    <%
        List<Task> todos = (List<Task>) request.getAttribute("todos");
        if (todos != null) {
            for (Task task : todos) {
    %>
    <tr>
        <td><%=task.getTitle()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus().toString()%>
        </td>
        <td><%=task.getTargetDate()%>
        </td>
        <td><%=task.getStartDate()%>
        </td>
        <td>
            <form action="/Todolist/edit/?id=<%=task.getId().toString()%>" method="post">
                <input type="submit" value="Edit"/>
            </form>
            <form action="/Todolist/?id=<%=task.getId().toString()%>" method="post">
                <input type="submit" value="Delete"/>
                <label>
                    <input hidden name="_method" value="DELETE"/>
                </label>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<nav>
    <%
        int totalPages = (Integer) request.getAttribute("totalPages");
        if (totalPages > 1) {
            for (int i = 1; i < totalPages + 1; i++) {
    %>
    <a href="/Todolist?page=<%=i%>"><%=i%>
    </a>
    <%
            }
        }
    %>
</nav>
</body>
</html>
