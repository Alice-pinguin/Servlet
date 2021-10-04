package ua.goit.repository;

import ua.goit.model.BaseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ua.goit.util.DatabaseConnector;
import ua.goit.util.PropertiesLoader;

import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class CrudRepositoryImpl<E extends BaseEntity<ID>, ID> implements Closeable, CrudRepository<E, ID> {

    private final Connection connection;

    private final ObjectMapper mapper;
    private final Class<E> modelClass;
    private final Map<String, String> columnFieldName;
    private final String databaseSchemaName;

    private final PreparedStatement findAllPreparedStatement;
    private final PreparedStatement findByIDPreparedStatement;
    private final PreparedStatement deletePreparedStatement;
    private final PreparedStatement createPreparedStatement;
    private final PreparedStatement updatePreparedStatement;

    @SneakyThrows
    public CrudRepositoryImpl(Class<E> modelClass) {

        this.connection = DatabaseConnector.getInstance().getConnection();
        this.databaseSchemaName = PropertiesLoader.getProperty("db.name");

        this.mapper = new ObjectMapper();

        this.modelClass = modelClass;
        this.columnFieldName = Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(field -> getColumnName(field), field -> field.getName()));

        String generatedColumns[] = {getColumnName(Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny().orElseThrow(() -> new RuntimeException("Entity must contais ID")))};

        String tableName = modelClass.getAnnotation(Entity.class) != null
                ? modelClass.getAnnotation(Entity.class).name() : modelClass.getSimpleName().toLowerCase();

        String countValues = IntStream.range(0, columnFieldName.size()).mapToObj(i -> "?").collect(Collectors.joining(","));
        String fieldsForCreate = columnFieldName.keySet().stream().collect(Collectors.joining(","));
        String fieldsForUpdate = columnFieldName.keySet().stream().map(v -> v + "=?").collect(Collectors.joining(","));

        this.findAllPreparedStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemaName + "." +
                tableName, generatedColumns);
        this.findByIDPreparedStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemaName + "." +
                tableName + " WHERE id=?", generatedColumns);
        this.deletePreparedStatement = connection.prepareStatement("DELETE FROM " + databaseSchemaName + "." +
                tableName + " WHERE id=?;", generatedColumns);
        this.createPreparedStatement = connection.prepareStatement("INSERT INTO " + databaseSchemaName + "." +
                tableName + "(" + fieldsForCreate + ")" + " VALUES (" + countValues + ")", generatedColumns);
        this.updatePreparedStatement = connection.prepareStatement("UPDATE " + databaseSchemaName + "." + tableName
                + " SET " + fieldsForUpdate + " WHERE id=?", generatedColumns);

    }
    private String getColumnName(Field field) {
        return field.getAnnotation(Column.class) == null ? field.getName() : field.getAnnotation(Column.class).name();
    }

    @SneakyThrows
    @Override
    public List<E> findAll() {
        return parse(findAllPreparedStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public Optional<E> findById(ID id) {
        findByIDPreparedStatement.setObject(1, id);
        final List<E> result = parse(findByIDPreparedStatement.executeQuery());
        if (result.isEmpty()) return Optional.empty();
        if (result.size() > 1) throw new RuntimeException("Method 'find by id' returned more than one result");
        return Optional.of(result.get(0));
    }

    @SneakyThrows
    @Override
    public void deleteById(ID id) {
        deletePreparedStatement.setObject(1, id);
        deletePreparedStatement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public E create(E e) {
     return executeStatement(createPreparedStatement, e);
  }

  @Override
   @SneakyThrows
   public E update(E e) {
       updatePreparedStatement.setObject(columnFieldName.size()+1, e.getId());
       return executeStatement(updatePreparedStatement, e);
  }

  @SneakyThrows
    private E executeStatement(PreparedStatement statement, E e) {
        int count = 1;
        for (String fieldName : columnFieldName.values ()) {
            Field declaredField = modelClass.getDeclaredField (fieldName);
            declaredField.setAccessible (true);
            statement.setObject (count++, declaredField.get (e));
        }
        statement.executeUpdate ();
        ResultSet rs = statement.getGeneratedKeys ();
        return findById (rs.next () ? (ID) rs.getObject (1) : e.getId ()).get ();
    }


        @SneakyThrows
    private List<E> parse(ResultSet resultSet) {
        final List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            final Map<String, Object> objectMap = new HashMap<>();
            for (String fieldName : columnFieldName.keySet()) {
                objectMap.put(columnFieldName.get(fieldName), resultSet.getObject(fieldName));
            }
            result.add(mapper.convertValue(objectMap, modelClass));
        }
        return result;
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }

}

