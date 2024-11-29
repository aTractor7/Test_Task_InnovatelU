package org.example.dao;

import java.util.Optional;

public interface AbstractDao <T>{

    void save(T entity);

    Optional<T> findById(String id);
}
