package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Result {

    private String parent;

    private String name;


    private String percentage;

    private String mixBowlPercentage;

    private String position;

    private boolean isComponent;

    private String total = "";

    private String totalSalt = "";

    private String totalWater = "";

    private boolean checked;

    private String allergen;



//    public Result(String parent, String name, String percentage, String mixBowlPercentage, String position) {
//        this.parent = parent;
//        this.name = name;
//        this.percentage = percentage;
//        this.mixBowlPercentage = mixBowlPercentage;
//        this.position = position;
//
//    }


    public Result(String parent, String name, String percentage, String position) {
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;

        this.position = position;


    }

    public Result(String parent, String name, String percentage, String mixBowlPercentage, String position, boolean isComponent) {
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;
        this.mixBowlPercentage = mixBowlPercentage;
        this.position = position;
        this.isComponent = isComponent;
    }

    public Result(String parent, String name, String percentage, String mixBowlPercentage, String position, boolean isComponent, String allergen) {
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;
        this.mixBowlPercentage = mixBowlPercentage;
        this.position = position;
        this.isComponent = isComponent;
        this.allergen = allergen;

    }


//    public Result(String parent, String name, String percentage, String position, boolean isComponent) {
//        this.parent = parent;
//        this.name = name;
//        this.percentage = percentage;
//
//        this.position = position;
//
//        this.isComponent = isComponent;
//    }
}
