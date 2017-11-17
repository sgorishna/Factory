package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Svetik on 08/10/2017.
 */
@org.springframework.stereotype.Component
@Getter
@Setter
@NoArgsConstructor
public class Result {

  private String parent;

  private String name;

  private double percentage;

  private String mixBowlPercentage;

  private String position;

//  public Result(String parent, String name, double percentage, String mixBowlPercentage){
//      this.parent = parent;
//      this.name = name;
//      this.percentage = percentage;
//      this.mixBowlPercentage = mixBowlPercentage;
//  }

    public Result(String parent, String name, double percentage, String mixBowlPercentage, String position){
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;
        this.mixBowlPercentage = mixBowlPercentage;
        this.position = position;
    }

    public Result(String parent, String name, double percentage, String position){
        this.parent = parent;
        this.name = name;
        this.percentage = percentage;

        this.position = position;
    }
}
