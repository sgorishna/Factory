package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface ProductService {

    void save(Product product) throws InvalidDataException;

    void delete(Product product);

    void update(Product product) throws InvalidDataException;

    Product findById(int id);

    List<Product> findAll();

    List<Product> findByName(String name);
}
