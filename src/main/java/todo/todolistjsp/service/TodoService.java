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

    public List<String> saveTask(TaskCreateDto task) {
        List<String> errors = task.validate();

        if (!errors.isEmpty()) {
            return errors;
        }

        repository.save(task);
        return errors;
    }

    public List<String> editTask(UUID id, TaskUpdateDto editedTasks) {
        List<String> errors = editedTasks.validate();

        if (!errors.isEmpty()) {
            return errors;
        }

        repository.edit(id, editedTasks);
        return errors;
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
