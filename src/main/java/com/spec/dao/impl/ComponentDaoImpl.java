package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.ComponentDao;
import com.spec.model.Component;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetik on 15/08/2017.
 */

@org.springframework.stereotype.Component
public class ComponentDaoImpl extends AbstractGenericDao<com.spec.model.Component> implements ComponentDao {

    @Transactional
    public List<Component> autocompleteComponentName(String name) {

        Criteria criteria = getSession().createCriteria(Component.class);
        criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return criteria.list();
    }


//    private SessionFactory sessionFactory;
//
//
//    public void save(Component c) {
//
//        Session session = this.sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        session.persist(c);
//        tx.commit();
//        session.close();
//    }
//    @SuppressWarnings("unchecked")
//    public List<Component> list() {
//        Session session = this.sessionFactory.openSession();
//        List<Component> compList = session.createQuery("from Component").list();
//
//        session.close();
//        return compList;
//
//    }


}
