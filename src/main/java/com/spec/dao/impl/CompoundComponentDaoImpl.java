package com.spec.dao.impl;


import com.spec.dao.AbstractGenericDao;
import com.spec.dao.CompoundComponentDao;
import com.spec.model.Compound;
import com.spec.model.CompoundComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Component
public class CompoundComponentDaoImpl extends AbstractGenericDao<CompoundComponent> implements CompoundComponentDao {


    public List<CompoundComponent> componentsByIdCompound(Compound compound) {
        return compound.getComponentListByIdCompound();
    }
}
