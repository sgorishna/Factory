package com.spec.service.impl;


import com.spec.dao.ProductCompoundDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.ProductCompound;
import com.spec.service.ProductCompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class ProductCompoundServiceImpl implements ProductCompoundService {

    @Autowired
    private ProductCompoundDao productCompoundDao;

    public ProductCompound findById(int id) {
        return productCompoundDao.findById(id);
    }

    private boolean checkCompoundDuplicate(ProductCompound productCompound) {

        boolean res = false;
        for (ProductCompound p : productCompound.getProduct().getCompoundsListByIdProduct()) {

            if(p.getCompound().equals(productCompound.getCompound())){

                res =true;
            }


        }

        return res;
    }


    public void save(ProductCompound productCompound)  throws InvalidDataException {

        if (checkCompoundDuplicate(productCompound)) {

            throw new InvalidDataException("duplicate compound '" + productCompound.getCompound().getName() + "'");
        } else
            productCompoundDao.save(productCompound);

    }

    public void delete(ProductCompound productCompound) {

        productCompoundDao.delete(productCompound);
    }

    public void update(ProductCompound productCompound) {

        productCompoundDao.update(productCompound);
    }


    public List<ProductCompound> findAll() {
        return productCompoundDao.findAll();
    }


}
