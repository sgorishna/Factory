package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.ProductDao;
import com.spec.model.Product;
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
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {

    @Transactional
    public List<Product> findByCode(String code) {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("code", code));
        return criteria.list();
    }

}
