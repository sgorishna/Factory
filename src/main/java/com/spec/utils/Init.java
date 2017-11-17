package com.spec.utils;


import com.spec.service.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Svetik on 23/08/2017.
 */
@Component
@Getter
public class Init {

    final
    ComponentService componentService;

    final
    CompoundService compoundService;


    final
    ProductService productService;

    final
    CompoundComponentService compoundComponentService;

    final
    CompoundCompoundService compoundCompoundService;

    final
    ProductComponentService productComponentService;

    final
    ProductCompoundService productCompoundService;


    @Autowired
    public Init(ComponentService componentService, CompoundService compoundService,
                ProductService productService, CompoundComponentService compoundComponentService, CompoundCompoundService compoundCompoundService,
                ProductComponentService productComponentService, ProductCompoundService productCompoundService) {
        this.componentService = componentService;
        this.compoundService = compoundService;
        this.productService = productService;
        this.compoundComponentService = compoundComponentService;
        this.compoundCompoundService = compoundCompoundService;
        this.productComponentService = productComponentService;
        this.productCompoundService = productCompoundService;


    }
}
