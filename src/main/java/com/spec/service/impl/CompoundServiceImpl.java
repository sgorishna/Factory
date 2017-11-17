package com.spec.service.impl;


import com.spec.dao.CompoundDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Component
public class CompoundServiceImpl implements CompoundService {

    private final CompoundDao compoundDao;

    @Autowired
    public CompoundServiceImpl(CompoundDao compoundDao) {
        this.compoundDao = compoundDao;
    }

    public void save(Compound compound) throws InvalidDataException {

        if (compoundDao.findByName(compound.getName()).isEmpty())
            compoundDao.save(compound);
        else {

            throw new InvalidDataException("Compound with name '" + compound.getName() + "' already exist. Please choose other name");
        }
    }

    public void update(Compound compound) throws InvalidDataException {
        if (compoundDao.findByName(compound.getName()).isEmpty())
            compoundDao.update(compound);

        else {

            throw new InvalidDataException("Compound with name '" + compound.getName() + "' already exist. Please choose other name");
        }
    }

    public void delete(Compound compound) {

        compoundDao.delete(compound);
    }

    public Compound findById(int id) {
        return compoundDao.findById(id);
    }

    public List<Compound> findAll() {
        return compoundDao.findAll();
    }

    public List<Compound> findByName(String name) {
        return compoundDao.findByName(name);
    }

    public List<String> autocompleteCompoundName(String name) {

        List<String> list = new ArrayList<String>();

        for (Compound compound : compoundDao.autocompleteCompoundName(name)) {

            list.add(compound.getName());
        }

        return list;
    }
}
