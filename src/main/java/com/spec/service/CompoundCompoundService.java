package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.CompoundCompound;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CompoundCompoundService {

    CompoundCompound findById(int id);

    void save(CompoundCompound compoundCompound) throws InvalidDataException;

    void delete(CompoundCompound compoundCompound);

void update(CompoundCompound compoundCompound);

    List<CompoundCompound> findAll();


}
