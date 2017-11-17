package com.spec.service.impl;


import com.spec.dao.CompoundCompoundDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.CompoundCompound;
import com.spec.service.CompoundCompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class CompoundCompoundServiceImpl implements CompoundCompoundService {

    @Autowired
    private CompoundCompoundDao compoundCompoundDao;

    public CompoundCompound findById(int id) {
        return compoundCompoundDao.findById(id);
    }

    private boolean checkChildCompoundDuplicate(CompoundCompound compoundCompound) {

        boolean res = false;
        for (CompoundCompound c : compoundCompound.getParent().getChildByParentId()) {

            if (c.getChild().getName().equals(compoundCompound.getChild().getName())) {

                res = true;
            }
        }

        return res;
    }

    public void save(CompoundCompound compoundCompound) throws InvalidDataException {

        if (checkChildCompoundDuplicate(compoundCompound)) {

            throw new InvalidDataException("duplicate compound '" + compoundCompound.getChild().getName() + "'");
        } else if (compoundCompound.getParent().getName().equals(compoundCompound.getChild().getName())) {
            throw new InvalidDataException("duplicate compound '" + compoundCompound.getChild().getName() + "'");
        } else
            compoundCompoundDao.save(compoundCompound);
    }

    public void delete(CompoundCompound compoundCompound) {

        compoundCompoundDao.delete(compoundCompound);
    }

    public void update(CompoundCompound compoundCompound) {

        compoundCompoundDao.update(compoundCompound);
    }


    public List<CompoundCompound> findAll() {
        return compoundCompoundDao.findAll();
    }


}
