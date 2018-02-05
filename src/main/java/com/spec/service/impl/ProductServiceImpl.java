package com.spec.service.impl;


import com.spec.dao.ProductDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;
import com.spec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(Product product) throws InvalidDataException {

        if(productDao.findByName(product.getName()).isEmpty())
            productDao.save(product);

         else{

            throw new InvalidDataException("Product with name '"+product.getName() + "' already exist. Please choose other name");
        }
    }

    public void delete(Product product) {

        productDao.delete(product);
    }

    public void update(Product product) throws InvalidDataException{


        Product found = productDao.findById(product.getId());

        if (!productDao.findByCode(product.getCode()).isEmpty() && !productDao.findByCode(product.getCode()).get(0).getCode().equals(found.getCode())) {

            throw new InvalidDataException("Product with code '" + product.getCode() + "' already exist. Please input other code");

        } else if (!productDao.findByName(product.getName()).isEmpty() && !productDao.findByName(product.getName()).get(0).getName().equals(found.getName())) {

            throw new InvalidDataException("Product with name '" + product.getName() + "' already exist. Please choose other name");

        } else {

            productDao.update(product);
        }
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public List<Product> findByCode(String code) {

        return productDao.findByCode(code);
    }

    public List<Product> findAllASC() {
        return productDao.findAllASC();
    }

    public List<Product> findByName(String name) {
        return productDao.findByName(name);
    }
}
