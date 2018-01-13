package com.spec.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Svetik on 21/12/2017.
 */
@Component
@Getter
public class Quid {

    public Quid() {


    }

    public Quid(String quidName, String quidPerc) {
        this.quidName = quidName;
        this.quidPerc = quidPerc;

    }


    public Quid(String declaredName, String parent, String order, String function, String allergen) {
        this.declaredName = declaredName;
        this.parent = parent;
        this.order = order;
        this.function = function;
        this.allergen = allergen;


    }

    private String productName;

    private String quidName;
    private String quidPerc;

    private String declaredName;
    private String parent;

    private String order;

    private String allergen;

    private String function;

    private List<String> arrQuidNames;

    private List<String> arrQuidPerc;

    private List<String> arrDeclaredNames;
    private List<String> arrParent;

    private List<String> arrOrder;

    private List<String> arrAllergens;

    private List<String> arrFunctions;

    private List<String> allQuid;

    private List<String> allDeclared;

    private List<String> allAllergens;

    private List<String> allFunctions;


}
