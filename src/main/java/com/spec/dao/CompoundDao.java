package com.spec.dao;


import com.spec.model.Compound;

import java.util.List;


public interface CompoundDao extends GenericDao<Compound> {

    List<Compound> autocompleteCompoundName(String name);

 
}
