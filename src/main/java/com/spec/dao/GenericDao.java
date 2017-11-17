package com.spec.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Svetik on 17/08/2017.
 */
public interface GenericDao<E> {

    void delete(E entity);

    void save(E entity);

    void update(E entity);

    E findById(Serializable id);

    List<E> findAll();

    List<E> findByName(String name);

}
