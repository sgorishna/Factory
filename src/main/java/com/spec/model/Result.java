package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@org.springframework.stereotype.Component
@Getter
@Setter
@NoArgsConstructor
public class Result {

  private String parent;

  private String name;

 // private double percentage;
    private String percentage;

  private String mixBowlPercentage;

  private String position;



    public Result(String parent, String name, String percentage, String mixBowlPercentage, String position){
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;
        this.mixBowlPercentage = mixBowlPercentage;
        this.position = position;
    }

//    public Result(String parent, String name, String percentage, String mixBowlPercentage, String position){
//        this.parent = parent;
//        this.name = name;
//        this.strPercentage = percentage;
//        this.mixBowlPercentage = mixBowlPercentage;
//        this.position = position;
//    }

    public Result(String parent, String name, String percentage, String position){
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;

        this.position = position;
    }
}
