package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Category;

import java.util.List;

/**
 * Created by Svetik on 19/08/2017.
 */
public interface CategoryService {

    void save(Category category) throws InvalidDataException;

    void update(Category category) throws InvalidDataException;

    void delete(Category category);

    Category findById(int id);

    List<Category> findAll();

    List<Category> findByName(String name);


}
