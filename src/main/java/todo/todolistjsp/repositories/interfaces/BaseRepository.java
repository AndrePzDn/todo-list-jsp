package todo.todolistjsp.repositories.interfaces;

import java.util.List;
import java.util.UUID;

import todo.todolistjsp.model.Entity;

public interface BaseRepository<T extends Entity> {
    void save(T data);
    T findById(UUID id);
    List<T> findAll();
    List<T> findAllPaginated(int size, int page);
    void edit(UUID id, T newData);
    void delete(UUID id);
}
