package todo.todolistjsp.repositories.concretes.inmemory;

import java.util.*;

import todo.todolistjsp.model.Entity;
import todo.todolistjsp.repositories.interfaces.BaseRepository;

public abstract class DataStructureBaseRepository<T extends Entity> implements BaseRepository<T> {

    protected final Map<UUID, T> dataRecord = new LinkedHashMap<>();

    @Override
    public void save(T data) {
        dataRecord.put(data.getId(), data);
    }

    @Override
    public T findById(UUID id) {
        return dataRecord.get(id);
    }

    @Override
    public List<T> findAll(int size, int page) {
        Collection<T> records = dataRecord.values();
        List<T> recordValues = new ArrayList<>(records);

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + page, recordValues.size());

        if (startIndex >= recordValues.size()) {
            return new ArrayList<>();
        }

        return recordValues.subList(startIndex, endIndex);
    }

    @Override
    public void edit(UUID id, T newData) {
        dataRecord.replace(id, newData);
    }

    @Override
    public void delete(UUID id) {
        dataRecord.remove(id);
    }
}
