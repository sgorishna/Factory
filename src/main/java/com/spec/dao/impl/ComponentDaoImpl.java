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

    @Transactional
    public List<Component> findByCode(String code) {
        Criteria criteria = getSession().createCriteria(Component.class);
        criteria.add(Restrictions.eq("code", code));
        return criteria.list();
    }


}
