package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.ProductCompound;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface ProductCompoundService {

    ProductCompound findById(int id);

    void save(ProductCompound productCompound) throws InvalidDataException;

    void delete(ProductCompound productCompound);

    void update(ProductCompound productCompound);

    List<ProductCompound> findAll();


}
