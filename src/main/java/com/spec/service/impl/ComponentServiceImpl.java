package com.spec.service.impl;


import com.spec.dao.ComponentDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Component;
import com.spec.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 19/08/2017.
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    private final ComponentDao dao;

    @Autowired
    public ComponentServiceImpl(ComponentDao dao) {
        this.dao = dao;
    }

    public void save(Component component) throws InvalidDataException {
        if(dao.findByName(component.getName()).isEmpty())

        dao.save(component);

        else{

            throw new InvalidDataException("Component with name '"+component.getName() + "' already exist. Please choose other name");
        }
    }

    public void update(Component component) throws InvalidDataException {
        if(dao.findByName(component.getName()).isEmpty())
            dao.update(component);

        else{

            throw new InvalidDataException("Component with name '"+component.getName() + "' already exist. Please choose other name");
        }
    }

    public void delete(Component component) {

        dao.delete(component);

    }

    public Component findById(int id) {

        return dao.findById(id);
    }

    public List<Component> findAll() {
        return dao.findAll();
    }

    public List<Component> findByName(String name) {
        return dao.findByName(name);
    }

    public List<String> autocompleteComponentName(String name) {

        List<String> list = new ArrayList<String>();

        for(Component component: dao.autocompleteComponentName(name)){

              list.add(component.getName());
        }

        return list;
    }
}
