package com.spec.dao;


import com.spec.model.Component;

import java.util.List;

/**
 * Created by Svetik on 15/08/2017.
 */
public interface ComponentDao extends GenericDao<Component>{

List<Component> autocompleteComponentName(String name);
}
