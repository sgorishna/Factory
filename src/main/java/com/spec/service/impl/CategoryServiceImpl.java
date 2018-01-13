package com.spec.service.impl;


import com.spec.dao.CategoryDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Category;
import com.spec.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 19/08/2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao dao;

    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        this.dao = dao;
    }

    public void save(Category category) throws InvalidDataException {
        if (dao.findByName(category.getName()).isEmpty())

            dao.save(category);

        else {

            throw new InvalidDataException("Component with name '" + category.getName() + "' already exist. Please choose other name");
        }
    }

    public void update(Category category) throws InvalidDataException {
        if (dao.findByName(category.getName()).isEmpty())
            dao.update(category);

        else {

            throw new InvalidDataException("Component with name '" + category.getName() + "' already exist. Please choose other name");
        }
    }

    public void delete(Category category) {

        dao.delete(category);

    }

    public Category findById(int id) {

        return dao.findById(id);
    }

    public List<Category> findAll() {
        return dao.findAll();
    }

    public List<Category> findByName(String name) {
        return dao.findByName(name);
    }


}
