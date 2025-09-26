<%@ page import="java.util.List" %>
<%@ page import="todo.todolistjsp.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Todo Application</title>
</head>
<style>
    <%@include file="../styles/home.css" %>
</style>
<body>
<jsp:include page="common/header.jsp"/>
<section class="list-container">
    <header class="list-header-action">
        <h2>Tasks</h2>
        <form action="/Todolist/new" method="post">
            <input type="submit" value="Add" class="btn"/>
        </form>
    </header>
    <table>
        <thead>
        <tr>
            <td>Title</td>
            <td>Description</td>
            <td>Status</td>
            <td>Target Date</td>
            <td>Start Date</td>
            <td>Actions</td>
        </tr>
        </thead>
        <tbody>
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
            <td class="action-container">
                <form action="/Todolist/edit/?id=<%=task.getId().toString()%>" method="post">
                    <input class="action-btn" type="submit" value="Edit"/>
                </form>
                <form action="/Todolist/?id=<%=task.getId().toString()%>" method="post">
                    <input class="action-btn" type="submit" value="Delete"/>
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
        </tbody>
    </table>
    <nav class="pagination-container ">
        <%
            int totalPages = (Integer) request.getAttribute("totalPages");
            if (totalPages > 1) {
                for (int i = 1; i < totalPages + 1; i++) {
        %>
        <a
                class="pagination-item <%=request.getParameter("page") != null &&  Integer.parseInt(request.getParameter("page")) == i ? "pagination-selected" : ""%>"
                href="/Todolist?page=<%=i%>"><%=i%>
        </a>
        <%
                }
            }
        %>
    </nav>
</section>
</body>
</html>
