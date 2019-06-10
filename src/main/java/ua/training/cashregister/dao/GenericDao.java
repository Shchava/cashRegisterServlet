package ua.training.cashregister.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable{
    boolean create (T entity);
    List<T> findAll();
    Optional<T> findById(long id);
    boolean update(T entity);
    boolean delete(long id);
    void close();
}
