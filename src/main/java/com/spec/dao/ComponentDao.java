package com.spec.dao;


import com.spec.model.Component;

import java.util.List;


public interface ComponentDao extends GenericDao<Component>{

List<Component> autocompleteComponentName(String name);

    List<Component> findByCode(String code);
}
