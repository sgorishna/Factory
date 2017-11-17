package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Users;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface UserService {

    void save(Users user) throws InvalidDataException;

    void delete(Users user);

    void update(Users user) throws InvalidDataException;

    void activate(String username);

    void deactivate(String username);


    List<Users> findAll();

    List<Users> findByLogin(String login);


}
