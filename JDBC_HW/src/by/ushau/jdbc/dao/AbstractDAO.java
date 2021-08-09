package by.ushau.jdbc.dao;

import by.ushau.jdbc.model.Entity;

import java.util.List;

public abstract class AbstractDAO<K, T extends Entity> {

    public abstract boolean create(T entity);

    public abstract boolean delete(K id);

    public abstract T update(T entity);

    public abstract List<T> findAll();

    public abstract T findEntityById(K id);

}
