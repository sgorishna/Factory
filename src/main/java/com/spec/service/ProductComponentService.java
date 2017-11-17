package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.ProductComponent;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface ProductComponentService {

    ProductComponent findById(int id);

    void save(ProductComponent productComponent) throws InvalidDataException;

    void delete(ProductComponent productComponent);

     void update(ProductComponent productComponent);

    List<ProductComponent> findAll();


}
