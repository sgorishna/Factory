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

        if (compound.getCode() == "" || compound.getCode() == null) {

            if (compoundDao.findByName(compound.getName()).isEmpty())
                compoundDao.save(compound);
            else {

                throw new InvalidDataException("Compound with name '" + compound.getName() + "' already exist. Please choose other name");
            }


        } else {

            if (compoundDao.findByCode(compound.getCode()).isEmpty())
                compoundDao.save(compound);
            else {

                throw new InvalidDataException("Compound with code '" + compound.getCode() + "' already exist. Please input other code");
            }


        }

//        if (compoundDao.findByName(compound.getName()).isEmpty())
//            compoundDao.save(compound);
//        else {
//
//            throw new InvalidDataException("Compound with name '" + compound.getName() + "' already exist. Please choose other name");
//        }
    }

    public void update(Compound compound) throws InvalidDataException {

        Compound found = compoundDao.findById(compound.getId());

        if(compound.getCode().equals("") && !compound.getCode().equals(found.getCode())){



                if (compoundDao.findByName(compound.getName()).isEmpty())


                    compoundDao.update(compound);
                else {

                    if(!compoundDao.findByName(compound.getName()).get(0).getCode().equals(compound.getCode())){

                        compoundDao.update(compound);
                    }
 else
                    throw new InvalidDataException("Compound with name '" + compound.getName() + "' already exist. Please choose other code");
                }



        }

        else if (!compound.getCode().equals(found.getCode()) ) {

            if (compoundDao.findByCode(compound.getCode()).isEmpty())
                compoundDao.update(compound);
            else {


                throw new InvalidDataException("Compound with code '" + compound.getCode() + "' already exist. Please input other code");
            }


        }
        else {
            compoundDao.update(compound);


        }

    }

    public void delete(Compound compound) {

        compoundDao.delete(compound);
    }

    public Compound findById(int id) {
        return compoundDao.findById(id);
    }

    public List<Compound> findAllASC() {
        return compoundDao.findAllASC();
    }

    public List<Compound> findByName(String name) {
        return compoundDao.findByName(name);
    }

    public List<Compound> findByCode(String code) {

        return compoundDao.findByCode(code);
    }

    public List<String> autocompleteCompoundName(String name) {

        List<String> list = new ArrayList<String>();

        for (Compound compound : compoundDao.autocompleteCompoundName(name)) {

            if (null == compound.getCode() || compound.getCode().equals("")) {

                list.add(compound.getName() + " - N/A ");
            } else
                list.add(compound.getName() + " - " + compound.getCode());
        }

        return list;
    }
}
