package com.spec.dao;


import com.spec.model.Users;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface UserDao extends GenericDao<Users> {

    List<Users> findByLogin(String login);


}
