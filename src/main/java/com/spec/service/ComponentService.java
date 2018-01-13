package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Component;

import java.util.List;

/**
 * Created by Svetik on 19/08/2017.
 */
public interface ComponentService {

    void save(Component component) throws InvalidDataException;

    void update(Component component) throws InvalidDataException;

    void delete(Component component);

    Component findById(int id);

    List<Component> findAll();

    List<Component> findByName(String name);

    List<Component> findByCode(String code);

    List<String> autocompleteComponentName(String name);
}
