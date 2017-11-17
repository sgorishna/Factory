package com.spec.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Svetik on 17/08/2017.
 */
@Component
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    public AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {

        return this.sessionFactory.getCurrentSession();
    }


    @Transactional
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Transactional
    public void save(E entity) {


        getSession().save(entity);

    }

    @Transactional
    public void update(E entity) {

        getSession().update(entity);

    }

    @Transactional
    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    @Transactional
    public List<E> findAll() {
        return getSession().createCriteria(this.entityClass).list();
    }

@Transactional
    public List<E> findByName(String name){

        return  getSession().createCriteria(this.entityClass).add(Restrictions.eq("name", name)).list();
    }
}

