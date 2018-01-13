package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.CategoryComponent;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CategoryComponentService {

    CategoryComponent findById(int id);

    void save(CategoryComponent categoryComponent) throws InvalidDataException;

    void delete(CategoryComponent categoryComponent);

    void update(CategoryComponent categoryComponent);

    List<CategoryComponent> findAll();


}
