package todo.todolistjsp.repositories.interfaces;

import java.util.UUID;

import todo.todolistjsp.dto.TaskCreateDto;
import todo.todolistjsp.dto.TaskUpdateDto;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;

public interface TaskRepository extends BaseRepository<Task, TaskCreateDto, TaskUpdateDto> {
    void updateTaskStatus(UUID id, Status status);
}
