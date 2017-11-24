package com.spec.utils;


import com.spec.model.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 23/08/2017.
 */
@Component
@Getter
public class Utils {


    private final Init init;


    private List<Result> result = new ArrayList<Result>();

    int position;

    int compoundPos;

    int componentPos;

    @Autowired
    public Utils(Init init) {

        this.init = init;
    }


    public void productComposition(Product product) {

        result.clear();
        position = 2;

        result.add(new Result(product.getName(), "Pork", "100.0", "1"));


        List<ProductCompound> compoundList = product.getCompoundsListByIdProduct();

        for (ProductCompound prod : compoundList) {


            result.add(new Result(prod.getProduct().getName(), prod.getCompound().getName(), String.valueOf(prod.getCompoundPercentage()), String.valueOf(position)));//new line
            compoundComposition(prod.getCompound().getChildByParentId(), position);

            componentPos = compoundPos;
            compoundComponentComposition(prod.getCompound().getComponentListByIdCompound(), String.valueOf(position));
            position++;
        }

        productComponentComposition(product);//new line
    }

/*    private void productCompoundComposition(Product p) {


        for (ProductCompound pc : p.getCompoundsListByIdProduct()) {


            result.add(new Result(p.getName(), pc.getCompound().getName(), pc.getCompoundPercentage(), String.valueOf(position)));

            position++;

        }

    }*/

    private void productComponentComposition(Product p) {


        for (ProductComponent pc : p.getComponentsListByIdProduct()) {


            result.add(new Result(p.getName(), pc.getComponent().getName(), String.valueOf(pc.getComponentPercentage()), String.valueOf(position)));

            position++;

        }

    }

    public void compoundComposition(Compound compound) {

        result.clear();


        compoundComposition(compound.getChildByParentId());
        compoundComponentComposition(compound.getComponentListByIdCompound());
    }


    private void compoundComposition(List<CompoundCompound> compounds, int position) {

        compoundPos = 1;
        componentPos = 1;


        /* subcompoundsProcessing */

        for (CompoundCompound compound : compounds) {


            result.add(new Result(compound.getParent().getName(), compound.getChild().getName(), String.valueOf(compound.getChildPercentage()), position + "." + compoundPos));


            List<CompoundComponent> list = compound.getChild().getComponentListByIdCompound();

            compoundComponentComposition(list, position + "." + compoundPos);
            compoundPos += 1;

        }
    }

    private void compoundComposition(List<CompoundCompound> compounds) {

        compoundPos = 1;
        componentPos = 1;


        /* subcompoundsProcessing */

        for (CompoundCompound compound : compounds) {


            result.add(new Result(compound.getParent().getName(), compound.getChild().getName(), String.valueOf(compound.getChildPercentage()), String.valueOf(compoundPos)));


            List<CompoundComponent> list = compound.getChild().getComponentListByIdCompound();

            compoundComponentComposition(list, String.valueOf(compoundPos));
            compoundPos += 1;

        }
    }


    private void compoundComponentComposition(List<CompoundComponent> components, String position) {

        int pos = componentPos;
        for (CompoundComponent component : components) {


            result.add(new Result(component.getCompound().getName(), component.getComponent().getName(), String.valueOf(component.getComponentPercentage()), position + "." + pos));
            pos += 1;

        }


    }

    private void compoundComponentComposition(List<CompoundComponent> components) {

        int pos = compoundPos;
        for (CompoundComponent component : components) {


            result.add(new Result(component.getCompound().getName(), component.getComponent().getName(), String.valueOf(component.getComponentPercentage()), String.valueOf(pos)));
            pos += 1;

        }


    }


}
