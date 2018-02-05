package com.spec.utils;

import com.spec.model.Category;
import com.spec.model.CategoryComponent;
import com.spec.model.Result;
import com.spec.service.CategoryComponentService;
import com.spec.service.CategoryService;
import com.spec.service.ComponentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Svetik on 24/11/2017.
 */

@Component
@Getter
public class Total {

    private final CategoryComponentService service;

    private final CategoryService categoryService;

    private final ComponentService componentService;

    @Autowired
    public Total(CategoryComponentService service, CategoryService categoryService, ComponentService componentService) {
        this.service = service;
        this.categoryService = categoryService;
        this.componentService = componentService;
    }


    public void total(List<Result> result) {

        for (Result res : result) {

            if (res.isComponent() == true) {

                if (inCategory(res) == true) {

                    exclusionFilter(result, res);

                } else if (endsWithCategory(res)) {

                    compareCheckedFalse(result, res);
                } else

                    compare(result, res);

            }
        }

    }

    public String brineTotal(String ingredient, List<Result> results){

        String total ="0.0";

        String productName = results.get(0).getParent();

        String parentBrineOrder="";

        for (Result res : results) {

            if(!parentBrine(res, productName).equals("")) {

                parentBrineOrder = parentBrine(res, productName);
            }

             if(res.isComponent() && res.getPosition().startsWith(parentBrineOrder)){

                 if(res.getName().toLowerCase().equals(ingredient)){

                     total = parse(total, res.getMixBowlPercentage());

                 }
             }

        }

        return total;
    }

    private String parentBrine(Result res, String productName){

        String brineOrder = "";

        if(res.getParent().equals(productName) && !res.getPosition().equals("1")){

            if(!res.isComponent() && res.getName().toLowerCase().contains("brine")) {

                brineOrder = res.getPosition();

            }
        }
return brineOrder;
    }

    public void totalForSalt(Result next, Result current, String mixBowl) {

        if (next.getName().equalsIgnoreCase("salt")) {

            current.setTotalSalt(parse(current.getTotal(), mixBowl));

        }

    }

    public void totalForWater(Result next, Result current, String mixBowl) {

        if (next.getName().equalsIgnoreCase("water")) {

            current.setTotalWater(parse(current.getTotal(), mixBowl));

        }

    }


    private void compare(List<Result> results, Result result) {


        for (Result res : results) {


            if (res.getName().equalsIgnoreCase(result.getName()) && res.isComponent()) {

                if (res.isChecked() == false) {

                    totalForSalt(res,result, res.getMixBowlPercentage());
                    totalForWater(res,result, res.getMixBowlPercentage());
                    result.setTotal(parse(result.getTotal(), res.getMixBowlPercentage()));

                    res.setChecked(true);

                }
            }
        }

    }

    private void compareCheckedFalse(List<Result> results, Result result) {


        for (Result res : results) {


            if (res.getName().equalsIgnoreCase(result.getName()) && res.isComponent()) {

                if (res.isChecked() == false) {

                    totalForSalt(res,result, res.getMixBowlPercentage());
                    totalForWater(res,result, res.getMixBowlPercentage());
                    result.setTotal(parse(result.getTotal(), res.getMixBowlPercentage()));


                }
            }
        }

    }

    private boolean endsWithCategory(Result res) {

        boolean result = false;

        List<Category> cats = categoryService.findAll();

        for (Category cat : cats) {

            if (res.getName().toLowerCase().endsWith(cat.getName().toLowerCase())) {

                return true;
            }
        }
        return result;
    }

    private void exclusionFilter(List<Result> results, Result result) {

        for (Result res : results) {


            if (res.getName().toLowerCase().endsWith(result.getName().toLowerCase()) && res.isComponent()) {

                if (res.isChecked() == false) {

                    totalForSalt(res,result, res.getMixBowlPercentage());
                    totalForWater(res,result, res.getMixBowlPercentage());

                    if (checkCategoryComponent(result.getName(), res.getName()) == false) {


                        result.setTotal(parse(result.getTotal(), res.getMixBowlPercentage()));

                        res.setChecked(true);
                    }

                }

            }

        }


    }

    private boolean checkCategoryComponent(String catName, String compName) {

        boolean res = false;

        Category cat = categoryService.findByName(catName).get(0);

        List<CategoryComponent> catcomp = cat.getComponentsByIdCategory();

        for (CategoryComponent c : catcomp) {


            if (c.getComponent().getName().equals(compName)) {

                res = true;
            }

        }

        return res;

    }

    private String parse(String total, String mixBowl) {

        if (total == "") {

            total = "0.0";
        }

        return String.valueOf(printDouble(round(Double.parseDouble(total) + Double.parseDouble(mixBowl))));
    }


    private double round(double value) {

        return (double) Math.round(value * 10000d) / 10000d;
    }


    private String printDouble(double value) {


        DecimalFormat formatter = new DecimalFormat("0.####");

        String result = formatter.format(Double.valueOf(value));

        return result;


    }

    private boolean inCategory(Result result) {

        if (!categoryService.findByName(result.getName()).isEmpty()) {

            return true;

        }
        return false;
    }


}
