package todo.todolistjsp.repositories.interfaces;

import java.util.List;
import java.util.UUID;

import todo.todolistjsp.dto.CreateDto;
import todo.todolistjsp.dto.UpdateDto;
import todo.todolistjsp.model.Entity;

public interface BaseRepository<E extends Entity, A extends CreateDto, U extends UpdateDto> {
    void save(A data);

    E findById(UUID id);

    List<E> findAll();

    List<E> findAllPaginated(int size, int page);

    void edit(UUID id, U newData);

    void delete(UUID id);
}
