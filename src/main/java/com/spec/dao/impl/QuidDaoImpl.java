package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.QuidDao;
import com.spec.model.Quid;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetik on 15/08/2017.
 */
@Setter
@Component
public class QuidDaoImpl extends AbstractGenericDao<Quid> implements QuidDao {


    @Transactional
    public List<Quid> findByProductId(int id) {
        Criteria criteria = getSession().createCriteria(Quid.class);
        criteria.add(Restrictions.eq("product_id", id));
        return criteria.list();
    }



}
