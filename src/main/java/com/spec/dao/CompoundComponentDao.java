package com.spec.dao;


import com.spec.model.Compound;
import com.spec.model.CompoundComponent;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CompoundComponentDao extends GenericDao<CompoundComponent> {

    List<CompoundComponent> componentsByIdCompound(Compound compound);
}
