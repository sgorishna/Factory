package com.spec.service;


import com.spec.exceptions.InvalidDataException;
import com.spec.model.Quid;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
public interface QuidService {

    void save(Quid quid) throws InvalidDataException;

    void delete(Quid quid);

    void update(Quid quid) throws InvalidDataException;

    Quid findById(int id);

    List<Quid> findByProductId(int id);


}
