<%@ page import="todo.todolistjsp.model.Task" %>
<%@ page import="todo.todolistjsp.model.Status" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>
        <%=request.getAttribute("action") == "edit" ? "Edit Task" : "Create Task"%>
    </title>
</head>
<style>
    body {
        padding: 0 0;
        margin: 0 0;
    }

    form {
        display: flex;
        flex-direction: column;
        border-width: 1px;
        border-color: black;
        width: 100%;
        max-width: 100%;
    }

    .input {
        display: flex;
        flex-direction: column;
    }
</style>
<body>
<jsp:include page="common/header.jsp"/>
<h2>
    <%=request.getAttribute("action") == "edit" ? "Edit Task" : "Create Task"%>
</h2>
<% Task task = ((Task) request.getAttribute("todo")); %>
<form
        action="<%="/Todolist/" + (task != null ? ("edit/?id=" + task.getId()) : "new")%>"
        method="post"
>
    <p class="input">
        <label for="title">Title</label>
        <input
                type="text"
                id="title"
                name="title"
                value="<%=task != null ? task.getTitle() : ""%>"
        />
    </p>
    <p class="input">
        <label for="description">Description</label>
        <input
                type="text"
                id="description"
                name="description"
                value="<%=task != null ? task.getDescription() : ""%>"
        />
    </p>
    <p class="input">
        <label for="status">Status</label>
        <select
                id="status"
                name="status"
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
    <p class="input">
        <label for="target-date">Target Date</label>
        <input
                id="target-date"
                type="date"
                name="targetDate"
                value="<%=task != null ? task.getTargetDate() : ""%>"
        />
    </p>
    <p class="input">
        <label for="start-date">Start Date</label>
        <input
                id="start-date"
                type="date"
                name="startDate"
                value="<%=task != null ? task.getStartDate() : ""%>"
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
</body>
</html>
