package com.spec.service.impl;


import com.spec.dao.CategoryComponentDao;
import com.spec.dao.CategoryDao;
import com.spec.dao.ComponentDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.CategoryComponent;
import com.spec.service.CategoryComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class CategoryComponentServiceImpl implements CategoryComponentService {

    private final CategoryComponentDao dao;

    private final CategoryDao categoryDao;

    private final ComponentDao componentDao;

    @Autowired
    public CategoryComponentServiceImpl(CategoryComponentDao dao, CategoryDao categoryDao, ComponentDao componentDao) {
        this.dao = dao;
        this.categoryDao = categoryDao;
        this.componentDao = componentDao;
    }


    public CategoryComponent findById(int id) {

        return dao.findById(id);
    }

    private boolean checkComponentDuplicate(CategoryComponent categoryComponent) {

        boolean res = false;
        for (CategoryComponent c : categoryComponent.getCategory().getComponentsByIdCategory()) {

            if (c.getComponent().getName().equals(categoryComponent.getComponent().getName())) {

                res = true;
            }
        }

        return res;
    }

    public void save(CategoryComponent categoryComponent) throws InvalidDataException {

        if (checkComponentDuplicate(categoryComponent)) {

            throw new InvalidDataException("duplicate component '" + categoryComponent.getComponent().getName() + "'");
        } else
            dao.save(categoryComponent);
    }

    public void delete(CategoryComponent categoryComponent) {

        dao.delete(categoryComponent);
    }

    public void update(CategoryComponent categoryComponent) {

        dao.update(categoryComponent);
    }


    public List<CategoryComponent> findAll() {
        return dao.findAll();
    }


}
