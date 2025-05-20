package com.project.loteria.dao;

import java.util.List;

public interface DAO<T> {
    T findById(String id);
    List<T> findAll();
    T insert(T obj);
    T save(T obj);
    void delete(String id);
}
