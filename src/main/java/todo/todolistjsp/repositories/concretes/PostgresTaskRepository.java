package todo.todolistjsp.repositories.concretes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import javax.sql.DataSource;

import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.repositories.interfaces.TaskRepository;

public class PostgresTaskRepository extends PostgresBaseRepository<Task> implements TaskRepository {

    // @Inject
    public PostgresTaskRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void updateTaskStatus(UUID id, Status status) {
        throw new UnsupportedOperationException("Unimplemented method 'updateTaskStatus'");
    }

    @Override
    protected String getTableName() {
        return "tasks";
    }

    @Override
    protected String getIdColumnName() {
        return "taskId";
    }

    @Override
    protected Task mapResult(ResultSet result) {
        Task task = new Task();
        try {
            task.setId(UUID.fromString(result.getString("taskId")));
            task.setTitle(result.getString("title"));
            task.setDescription(result.getString("description"));
            task.setStatus(Status.valueOf(result.getString("status")));
            task.setTargetDate(LocalDate.parse(result.getString("targetDate")));
            task.setStartDate(LocalDate.parse(result.getString("StartDate")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Task entity) {
        try {
            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getTitle());
            ps.setObject(3, entity.getDescription());
            ps.setObject(4, entity.getStatus().name());
            ps.setObject(5, entity.getTargetDate());
            ps.setObject(6, entity.getStartDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUpdateParametes(PreparedStatement ps, Task entity) {
        try {
            ps.setObject(1, entity.getTitle());
            ps.setObject(2, entity.getDescription());
            ps.setObject(3, entity.getStatus().name());
            ps.setObject(4, entity.getTargetDate());
            ps.setObject(5, entity.getStartDate());
            ps.setObject(6, entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getInsertSqlQuery() {
        return String.format("INSERT INTO %s VALUES (? ,?, ?, ?, ?, ?)", getTableName());
    }

    @Override
    protected String getUpdateSqlQuery() {
        return String.format(
                "UPDATE %s SET title = ?, description = ?, status = ?, targetDate = ?, startDate = ? WHERE %s = ?",
                getTableName(), getIdColumnName());
    }
}
