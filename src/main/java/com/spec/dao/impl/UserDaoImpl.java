package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.UserDao;
import com.spec.model.Users;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Component
public class UserDaoImpl extends AbstractGenericDao<Users> implements UserDao {

    @Transactional
    public List<Users> findByLogin(String login) {
        Criteria criteria = getSession().createCriteria(Users.class);
        criteria.add(Restrictions.eq("username", login));
        return criteria.list();


    }


}
