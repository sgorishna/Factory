package com.spec.dao;


import com.spec.model.Compound;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CompoundDao extends GenericDao<Compound> {

    List<Compound> autocompleteCompoundName(String name);

 
}
