package todo.todolistjsp.mapper;

import todo.todolistjsp.dto.CreateDto;
import todo.todolistjsp.dto.UpdateDto;
import todo.todolistjsp.model.Entity;

public interface Mapper<A extends CreateDto, U extends UpdateDto, E extends Entity> {
    E fromCreateDto(A dto);
    void updateEntity(E entity, U dto);
}
