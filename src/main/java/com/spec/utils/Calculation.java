package com.spec.utils;

import com.spec.model.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.spec.utils.WebappConstants.PORK_PERCENTAGE;

/**
 * Created by Svetik on 04/10/2017.
 */
@Component
@Getter
public class Calculation {


    private final Init init;

    private List<Result> result = new ArrayList<Result>();

    int position;

    int compoundPos;

    int componentPos;

    @Autowired
    public Calculation(Init init) {

        this.init = init;
    }

    public double getPorkPMP(Product p) {

        double res = PORK_PERCENTAGE;

        for (ProductComponent pc : p.getComponentsListByIdProduct()) {

            res += pc.getComponentPercentage();
        }

        for (ProductCompound pc : p.getCompoundsListByIdProduct()) {

            res += pc.getCompoundPercentage();
        }

        return res;
    }

    public double getPorkFinishedProduct(double porkPMP) {

        return round(100 / porkPMP * 100);

    }

    public void setProductComponentsMBP(Product p, double porkPMB, double porkPercentage) {


        for (ProductComponent pc : p.getComponentsListByIdProduct()) {

            pc.setMixedBowlPercentage(pc.getComponentPercentage() / porkPMB * porkPercentage);

            result.add(new Result(p.getName(), pc.getComponent().getName(), pc.getComponentPercentage(), printDouble(pc.getMixedBowlPercentage()), String.valueOf(position)));

            position++;


        }

    }

    public void setProductCompoundsMBP(Product p, double porkPMB, double porkPercentage) {


        for (ProductCompound pc : p.getCompoundsListByIdProduct()) {

            pc.setMixedBowlPercentage(pc.getCompoundPercentage() / porkPMB * porkPercentage);

            result.add(new Result(p.getName(), pc.getCompound().getName(), pc.getCompoundPercentage(), printDouble(pc.getMixedBowlPercentage()), String.valueOf(position)));

            position++;

        }

    }


    public void finishedProductPercentage(Product product) {

        result.clear();
        position = 2;
        double porkPMB = getPorkPMP(product);

        result.add(new Result(product.getName(), "Pork", porkPMB, printDouble(getPorkFinishedProduct(porkPMB)), "1"));

        setProductCompoundsMBP(product, porkPMB, PORK_PERCENTAGE);
        setProductComponentsMBP(product, porkPMB, PORK_PERCENTAGE);

        position = 2;
        double parentPercentage;

        List<ProductCompound> compoundList = product.getCompoundsListByIdProduct();

        for (ProductCompound prod : compoundList) {

            parentPercentage = prod.getMixedBowlPercentage();

            compoundMixBowl(prod.getCompound().getChildByParentId(), parentPercentage, position);

            componentPos = compoundPos;
            componentMixBowl(prod.getCompound().getComponentListByIdCompound(), parentPercentage, String.valueOf(position));
            position++;
        }


    }


    private void compoundMixBowl(List<CompoundCompound> compounds, double parentPercentage, int position) {

        compoundPos = 1;
        componentPos = 1;
        double parent = parentPercentage;

        /* subcompoundsProcessing */

        for (CompoundCompound compound : compounds) {


            compound.setMixedBowlPercentage(compound.getChildPercentage() * parent / 100);

            result.add(new Result(compound.getParent().getName(), compound.getChild().getName(), compound.getChildPercentage(), printDouble(compound.getMixedBowlPercentage()), position + "." + compoundPos));


//            System.out.println(compound.getParent().getName() + ": Compound: " + compound.getChild().getName() + " " + compound.getChildPercentage() + " " + printDouble(compound.getMixedBowlPercentage()));

            while (hasChild(compound.getChild()) != false) {

                parent = compound.getMixedBowlPercentage();

            }


//TODO must be subcomponents processing
//текущий step

            List<CompoundComponent> list = compound.getChild().getComponentListByIdCompound();

            componentMixBowl(list, compound.getMixedBowlPercentage(), position + "." + compoundPos);
            compoundPos += 1;

        }
    }

    private void componentMixBowl(List<CompoundComponent> components, double parentPercentage, String position) {

        int pos = componentPos;
        for (CompoundComponent component : components) {

            component.setMixedBowlPercentage(component.getComponentPercentage() * parentPercentage / 100);

            result.add(new Result(component.getCompound().getName(), component.getComponent().getName(), component.getComponentPercentage(), printDouble(component.getMixedBowlPercentage()), position + "." + pos));
            pos += 1;
            //  System.out.println(component.getCompound().getName() + ": -----Component: " + component.getComponent().getName() + " " + component.getComponentPercentage() + " " + printDouble(component.getMixedBowlPercentage()));
            // init.getCompoundComponentService().update(component);
        }


    }

    private boolean hasChild(Compound compound) {

        if (!compound.getChildByParentId().isEmpty())
            return true;
        else

            return false;
    }

    private double round(double value) {

        return (double) Math.round(value * 1000d) / 1000d;
    }

    private String printDouble(double value) {

        DecimalFormat formatter = new DecimalFormat("0.000");

        return formatter.format(round(value));
    }
}
