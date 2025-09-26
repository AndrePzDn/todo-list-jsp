package todo.todolistjsp.repositories.concretes.inmemory;

import java.util.UUID;

import todo.todolistjsp.model.Status;
import todo.todolistjsp.model.Task;
import todo.todolistjsp.repositories.interfaces.TaskRepository;

public class DataStructureTaskRepository extends DataStructureBaseRepository<Task> implements TaskRepository {

    @Override
    public void updateTaskStatus(UUID id, Status status) {
        Task task = dataRecord.get(id);
        task.setStatus(status);
        dataRecord.replace(id, task);
    }
}
