package com.spec.service.impl;


import com.spec.dao.UserDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Users;
import com.spec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(Users user) throws InvalidDataException {

        if (userDao.findByLogin(user.getUsername()).isEmpty()) {
            userDao.save(user);
        } else {

            throw new InvalidDataException("User with login '" + user.getUsername() + "' already exist. Please choose other login");
        }
    }

    public void delete(Users user) {

        userDao.delete(user);
    }

    public void update(Users user) throws InvalidDataException {


        if (userDao.findByLogin(user.getUsername()).isEmpty())
            userDao.update(user);

        else {

            throw new InvalidDataException("User with login '" + user.getUsername() + "' already exist. Please choose other login");
        }
    }

    public void activate(String username) {

        Users user = userDao.findByLogin(username).get(0);

        user.setActive((byte)1);

        userDao.update(user);

    }

    public void deactivate(String username) {

        Users user = userDao.findByLogin(username).get(0);

        user.setActive((byte)0);

        userDao.update(user);

    }


    public List<Users> findAll() {
        return userDao.findAll();
    }

    public List<Users> findByLogin(String login) {
        return userDao.findByLogin(login);
    }


}
