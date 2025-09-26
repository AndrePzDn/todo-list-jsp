package todo.todolistjsp.service;

import todo.todolistjsp.dto.TaskCreateDto;
import todo.todolistjsp.dto.TaskUpdateDto;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.repositories.interfaces.TaskRepository;
import todo.todolistjsp.model.Task;

import java.util.List;
import java.util.UUID;

public class TodoService {
    private final TaskRepository repository;

    // @Inject
    public TodoService(TaskRepository repository) {
        this.repository = repository;
    }

    public void saveTask(TaskCreateDto task) {
        repository.save(task);
    }

    public void editTask(UUID id, TaskUpdateDto editedTasks) {
        repository.edit(id, editedTasks);
    }

    public void deleteTask(UUID id) {
        repository.delete(id);
    }

    public List<Task> findAllTaskPaginated(int page, int size) {
        return repository.findAllPaginated(size, page);
    }

    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    public Task findTaskById(UUID id) {
        return repository.findById(id);
    }

    public void updateStatus(UUID id, Status status) {
        repository.updateTaskStatus(id, status);
    }
}
