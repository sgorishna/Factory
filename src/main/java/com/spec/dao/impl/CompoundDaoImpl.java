package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.CompoundDao;
import com.spec.model.Compound;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Component
public class CompoundDaoImpl extends AbstractGenericDao<Compound> implements CompoundDao {

    @Transactional
    public List<Compound> autocompleteCompoundName(String name) {
        Criteria criteria = getSession().createCriteria(Compound.class);
        criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return criteria.list();
    }

    @Transactional
    public List<Compound> findByCode(String code) {
        Criteria criteria = getSession().createCriteria(Compound.class);
        criteria.add(Restrictions.eq("code", code));
        return criteria.list();
    }
}
