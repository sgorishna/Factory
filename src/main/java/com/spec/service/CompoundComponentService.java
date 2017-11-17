package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.model.CompoundComponent;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CompoundComponentService {

    CompoundComponent findById(int id);

    void save(CompoundComponent compoundComponent) throws InvalidDataException;

    void delete(CompoundComponent compoundComponent);

    void update(CompoundComponent compoundComponent);

    List<CompoundComponent> findAll();
    
    List<CompoundComponent> componentsByIdCompound(Compound compound);


}
