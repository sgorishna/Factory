package com.spec.service.impl;


import com.spec.dao.ProductComponentDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.ProductComponent;
import com.spec.service.ProductComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class ProductComponentServiceImpl implements ProductComponentService {

    @Autowired
    private ProductComponentDao productComponentDao;

    public ProductComponent findById(int id) {
        return productComponentDao.findById(id);
    }

    private boolean checkComponentDuplicate(ProductComponent productComponent) {

        boolean res = false;
        for (ProductComponent p : productComponent.getProduct().getComponentsListByIdProduct()) {

            if (p.getComponent().getName().equals(productComponent.getComponent().getName())) {

                res = true;
            }
        }

        return res;
    }

    public void save(ProductComponent productComponent) throws InvalidDataException {

        if (checkComponentDuplicate(productComponent)) {

            throw new InvalidDataException("duplicate component '" + productComponent.getComponent().getName() + "'");
        } else
            productComponentDao.save(productComponent);
    }

    public void delete(ProductComponent productComponent) {

        productComponentDao.delete(productComponent);
    }

    public void update(ProductComponent productComponent) {

        productComponentDao.update(productComponent);
    }

    public List<ProductComponent> findAll() {
        return productComponentDao.findAll();
    }


}
