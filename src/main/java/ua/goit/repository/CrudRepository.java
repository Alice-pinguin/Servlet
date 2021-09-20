package ua.goit.repository;

import ua.goit.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends BaseEntity<ID>, ID> {

    E save(E e);  //(instead of create and update)

    List<E> saveAll(Iterable<E> itrb);

    void deleteById(ID id);

    Optional<E> findById(ID id);

    List<E> findAll();

}
