package todo.todolistjsp.repositories.interfaces;

import java.util.UUID;

import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;

public interface TaskRepository extends BaseRepository<Task> {
    void updateTaskStatus(UUID id, Status status);
}
