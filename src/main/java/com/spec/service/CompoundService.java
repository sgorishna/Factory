package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface CompoundService {

    void save(Compound compound) throws InvalidDataException;

    void update(Compound compound) throws InvalidDataException;

    void delete(Compound compound);

    Compound findById(int id);

    List<Compound> findAll();

    List<Compound> findByName(String name);

    List<String> autocompleteCompoundName(String name);
}