package com.spec.dao;


import com.spec.model.Compound;
import com.spec.model.CompoundComponent;

import java.util.List;


public interface CompoundComponentDao extends GenericDao<CompoundComponent> {

    List<CompoundComponent> componentsByIdCompound(Compound compound);
}
