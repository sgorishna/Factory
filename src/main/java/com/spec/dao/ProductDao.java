package com.spec.dao;


import com.spec.model.Product;

import java.util.List;


public interface ProductDao extends GenericDao<Product> {

    List<Product> findByCode(String code);
    
}
