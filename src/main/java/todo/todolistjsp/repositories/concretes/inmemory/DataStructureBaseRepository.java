package todo.todolistjsp.repositories.concretes.inmemory;

import java.util.*;

import todo.todolistjsp.dto.CreateDto;
import todo.todolistjsp.dto.UpdateDto;
import todo.todolistjsp.mapper.Mapper;
import todo.todolistjsp.model.Entity;
import todo.todolistjsp.repositories.interfaces.BaseRepository;

public abstract class DataStructureBaseRepository<T extends Entity, A extends CreateDto, U extends UpdateDto>
        implements BaseRepository<T, A, U> {

    private final Mapper<A, U, T> mapper;
    protected final Map<UUID, T> dataRecord = new LinkedHashMap<>();

    public DataStructureBaseRepository(Mapper<A, U, T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(A data) {
        T entity = mapper.fromCreateDto(data);
        dataRecord.put(entity.getId(), entity);
    }

    @Override
    public T findById(UUID id) {
        return dataRecord.get(id);
    }

    @Override
    public List<T> findAll() {
        Collection<T> records = dataRecord.values();
        return new ArrayList<>(records);
    }

    @Override
    public List<T> findAllPaginated(int size, int page) {
        Collection<T> records = dataRecord.values();
        List<T> recordValues = new ArrayList<>(records);

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, recordValues.size());

        if (startIndex >= recordValues.size()) {
            return new ArrayList<>();
        }

        return recordValues.subList(startIndex, endIndex);
    }

    @Override
    public void edit(UUID id, U newData) {
        T entity = dataRecord.get(id);

        if (entity == null) {
            return;
        }

        mapper.updateEntity(entity, newData);
    }

    @Override
    public void delete(UUID id) {
        dataRecord.remove(id);
    }
}
