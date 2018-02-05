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
        if (dao.findByName(component.getName()).isEmpty()) {

            if (dao.findByCode(component.getCode()).isEmpty()) {

                dao.save(component);
            } else {

                throw new InvalidDataException("Component with code '" + component.getCode() + "' already exist. Please input other code");
            }
        } else {

            throw new InvalidDataException("Component with name '" + component.getName() + "' already exist. Please choose other name");
        }
    }

    public void update(Component component) throws InvalidDataException {

        Component found = dao.findById(component.getId());

        if (!dao.findByCode(component.getCode()).isEmpty() && !dao.findByCode(component.getCode()).get(0).getCode().equals(found.getCode())) {

            if (!component.getCode().equals(""))


                throw new InvalidDataException("Component with code '" + component.getCode() + "' already exist. Please input other code");
            else {

                dao.update(component);
            }

        } else if (!dao.findByName(component.getName()).isEmpty() && !dao.findByName(component.getName()).get(0).getName().equals(found.getName())) {

            throw new InvalidDataException("Component with name '" + component.getName() + "' already exist. Please choose other name");

        } else {

            dao.update(component);
        }

    }

    public void delete(Component component) {

        dao.delete(component);

    }

    public Component findById(int id) {

        return dao.findById(id);
    }

    public List<Component> findAllASC() {
        return dao.findAllASC();
    }

    public List<Component> findByName(String name) {
        return dao.findByName(name);
    }

    public List<Component> findByCode(String code) {

        return dao.findByCode(code);
    }

    public List<String> autocompleteComponentName(String name) {

        List<String> list = new ArrayList<String>();

        for (Component component : dao.autocompleteComponentName(name)) {

            if (null == component.getCode()|| component.getCode().equals("")) {

                list.add(component.getName() + " - N/A ");
            } else
                list.add(component.getName() + " - " + component.getCode());
        }

        return list;
    }


}
