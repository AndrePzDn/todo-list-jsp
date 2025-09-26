package todo.todolistjsp.repositories.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import todo.todolistjsp.dto.CreateDto;
import todo.todolistjsp.dto.UpdateDto;
import todo.todolistjsp.mapper.Mapper;
import todo.todolistjsp.model.Entity;
import todo.todolistjsp.repositories.interfaces.BaseRepository;

public abstract class PostgresBaseRepository<T extends Entity, A extends CreateDto, U extends UpdateDto>
        implements BaseRepository<T, A, U> {

    protected DataSource dataSource;
    private final Mapper<A, U, T> mapper;

    public PostgresBaseRepository(DataSource dataSource, Mapper<A, U, T> mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract T mapResult(ResultSet result);

    protected abstract void setInsertParameters(PreparedStatement ps, T entity);

    protected abstract void setUpdateParametes(PreparedStatement ps, T entity);

    protected abstract String getInsertSqlQuery();

    protected abstract String getUpdateSqlQuery();

    @Override
    public void save(A data) {
        String sqlQuery = getInsertSqlQuery();
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            T entity = mapper.fromCreateDto(data);
            setInsertParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T findById(UUID id) {
        String sqlQUery = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQUery)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return mapResult(resultSet);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<T> findAll() {
        String sqlQuery = "SELECT * FROM " + getTableName();
        List<T> results = new ArrayList<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                results.add(mapResult(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    @Override
    public List<T> findAllPaginated(int size, int page) {
        String sqlQuery = String.format("SELECT * FROM %s LIMIT %s OFFSET %s", getTableName(), size, (page - 1) * size);
        List<T> results = new ArrayList<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                results.add(mapResult(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    @Override
    public void edit(UUID id, U newData) {
        String sqlQuery = getUpdateSqlQuery();
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            T entity = findById(id);

            if (entity == null)
                return;

            mapper.updateEntity(entity, newData);
            setUpdateParametes(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s = ?", getTableName(), getIdColumnName());

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
