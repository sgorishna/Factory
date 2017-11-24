package com.spec.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<E> {

    void delete(E entity);

    void save(E entity);

    void update(E entity);

    E findById(Serializable id);

    List<E> findAll();

    List<E> findByName(String name);

}
