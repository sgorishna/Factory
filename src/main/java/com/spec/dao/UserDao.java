package com.spec.dao;


import com.spec.model.Users;

import java.util.List;

public interface UserDao extends GenericDao<Users> {

    List<Users> findByLogin(String login);


}
