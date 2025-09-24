package todo.todolistjsp.service;

import todo.todolistjsp.repositories.concretes.inmemory.DataStructureTaskRepository;
import todo.todolistjsp.repositories.interfaces.TaskRepository;

public class RepositoryManager {
    private final TaskRepository taskRepository = new DataStructureTaskRepository();

    private static RepositoryManager instance;

    public static RepositoryManager getInstance() {
        if (instance == null) {
            instance = new RepositoryManager();
        }

        return instance;
    }

    public static TaskRepository getTaskRepository() {
        return getInstance().taskRepository;
    }
}
