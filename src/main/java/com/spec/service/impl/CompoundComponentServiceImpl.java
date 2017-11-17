package com.spec.service.impl;


import com.spec.dao.ComponentDao;
import com.spec.dao.CompoundComponentDao;
import com.spec.dao.CompoundDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.model.CompoundComponent;
import com.spec.service.CompoundComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class CompoundComponentServiceImpl implements CompoundComponentService {

    private final CompoundComponentDao dao;

    private final ComponentDao componentDao;

    private final CompoundDao compoundDao;

    @Autowired
    public CompoundComponentServiceImpl(CompoundComponentDao dao, ComponentDao componentDao, CompoundDao compoundDao) {
        this.dao = dao;
        this.componentDao = componentDao;
        this.compoundDao = compoundDao;
    }


    public CompoundComponent findById(int id) {

        return dao.findById(id);
    }

    private boolean checkComponentDuplicate(CompoundComponent compoundComponent) {

        boolean res = false;
        for (CompoundComponent c : compoundComponent.getCompound().getComponentListByIdCompound()) {

            if (c.getComponent().getName().equals(compoundComponent.getComponent().getName())) {

                res = true;
            }
        }

        return res;
    }

    public void save(CompoundComponent compoundComponent) throws InvalidDataException {

        if (checkComponentDuplicate(compoundComponent)) {

            throw new InvalidDataException("duplicate component '" + compoundComponent.getComponent().getName() + "'");
        } else
            dao.save(compoundComponent);
    }

    public void delete(CompoundComponent compoundComponent) {

        dao.delete(compoundComponent);
    }

    public void update(CompoundComponent compoundComponent) {

        dao.update(compoundComponent);
    }


    public List<CompoundComponent> findAll() {
        return dao.findAll();
    }


    public List<CompoundComponent> componentsByIdCompound(Compound compound) {
        return dao.componentsByIdCompound(compound);
    }


}
