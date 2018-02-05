package com.spec.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Svetik on 21/12/2017.
 */
@Component
@Getter
public class QuidUtils {

    public QuidUtils() {


    }



    public QuidUtils(String quidName, String quidPerc, String order) {
        this.quidName = quidName;
        this.quidPerc = quidPerc;
        this.order = order;


    }


    public QuidUtils(String declaredName, String parent, String order, String function, String allergen) {
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

    private List<String> quidColumn;

    private List<String> declaredColumn;

    private List<String> allergenColumn;

    private List<String> functionColumn;

    private List<String> arrQuidOrder;

//    @Override
//    public boolean equals(Object o) {
//
//        boolean res =true;
//        if (this == o) return res;
//        if (o == null || getClass() != o.getClass())
//            res= false;
//
//        Quid quid = (Quid) o;
//
////        if (this.getQuidName() != quid.getDeclaredName()) {
////            res = false;
////        }
//        if (!this.getOrder().equalsIgnoreCase(quid.getOrder())) {
//            res = false;
//        }
////        return (getCode() != null) && getCode().equals(compound.getCode()) || getCode() == null && (compound.getCode() == null);
//        return res;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getQuidName().hashCode();
//        result = 31 * result + getParent().hashCode();
//        result = 31 * result + getOrder().hashCode();
//        return result;
//    }

}
