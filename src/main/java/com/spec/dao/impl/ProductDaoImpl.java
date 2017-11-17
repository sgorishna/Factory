package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.ProductDao;
import com.spec.model.Product;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Svetik on 15/08/2017.
 */
@Setter
@Component
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {



}
