package com.spec.dao;


import com.spec.model.Quid;

import java.util.List;


public interface QuidDao extends GenericDao<Quid> {

    List<Quid> findByProductId(int id);

}
