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

    String compoundInline;
    String componentInline;

   // String separator = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
   String separator ="<p style=\"padding-left:40px\">";
   // String separatorStep = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
   String separatorStep = "<p style=\"padding-left:80px\">";

    @Autowired
    public Calculation(Init init) {

        this.init = init;
    }

    private double getPorkPMP(Product p) {

        double res = PORK_PERCENTAGE;

        for (ProductComponent pc : p.getComponentsListByIdProduct()) {

            res += pc.getComponentPercentage();
        }

        for (ProductCompound pc : p.getCompoundsListByIdProduct()) {

            res += pc.getCompoundPercentage();
        }

        return res;
    }

    private double getPorkFinishedProduct(double porkPMP) {

        return round(100 / porkPMP * 100);

    }

    private void setProductComponentsMBP(Product p, double porkPMB, double porkPercentage) {


        for (ProductComponent pc : p.getComponentsListByIdProduct()) {

            pc.setMixedBowlPercentage(pc.getComponentPercentage() / porkPMB * porkPercentage);

            result.add(new Result(p.getName(), pc.getComponent().getName(), String.valueOf(pc.getComponentPercentage()), printDouble(pc.getMixedBowlPercentage()), String.valueOf(position)));

            position++;


        }

    }

    private void setProductCompoundsMBP(Product p, double porkPMB, double porkPercentage) {


        for (ProductCompound pc : p.getCompoundsListByIdProduct()) {

            pc.setMixedBowlPercentage(pc.getCompoundPercentage() / porkPMB * porkPercentage);


        }

    }


    public void finishedProductPercentage(Product product) {

        result.clear();
        position = 2;
        double porkPMB = getPorkPMP(product);

        result.add(new Result(product.getName(), "Pork", "100.0", printDouble(getPorkFinishedProduct(porkPMB)), "1"));

        setProductCompoundsMBP(product, porkPMB, PORK_PERCENTAGE);

        double parentPercentage;

        List<ProductCompound> compoundList = product.getCompoundsListByIdProduct();

        for (ProductCompound prod : compoundList) {

            parentPercentage = prod.getMixedBowlPercentage();

            result.add(new Result(prod.getProduct().getName(), prod.getCompound().getName(), String.valueOf(prod.getCompoundPercentage()), printDouble(prod.getMixedBowlPercentage()), String.valueOf(position)));
            compoundMixBowl(prod.getCompound().getChildByParentId(), parentPercentage, position);

            componentPos = compoundPos;
           componentInline = separator;
            componentMixBowl(prod.getCompound().getComponentListByIdCompound(), parentPercentage, String.valueOf(position));
            position++;
        }

        setProductComponentsMBP(product, porkPMB, PORK_PERCENTAGE);
    }


    private void compoundMixBowl(List<CompoundCompound> compounds, double parentPercentage, int position) {

        compoundPos = 1;
        componentPos = 1;

        compoundInline = separator;
        componentInline = separator+separatorStep;
        double parent = parentPercentage;

        /* subcompoundsProcessing */

        for (CompoundCompound compound : compounds) {


            compound.setMixedBowlPercentage(compound.getChildPercentage() * parent / 100);

            result.add(new Result(compound.getParent().getName(),compoundInline+compound.getChild().getName(), compoundInline+String.valueOf(compound.getChildPercentage()), compoundInline+printDouble(compound.getMixedBowlPercentage()), position + "." + compoundPos));


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

            result.add(new Result(component.getCompound().getName(), componentInline+component.getComponent().getName(), componentInline+String.valueOf(component.getComponentPercentage()), componentInline+printDouble(component.getMixedBowlPercentage()), position + "." + pos));
            pos += 1;

        }


    }

    private boolean hasChild(Compound compound) {

        return !compound.getChildByParentId().isEmpty();
    }

    private double round(double value) {

        return (double) Math.round(value * 10000d) / 10000d;
    }

    private String printDouble(double value) {

      //  return String.valueOf(round(value)).replaceAll("[0]*$", "").replaceAll(".$", "");

        DecimalFormat formatter = new DecimalFormat("0.####");

        String result = formatter.format(Double.valueOf(value));

        return result;

//        double prepare =Double.parseDouble(formatter.format(round(value)));
//
//        return String.valueOf(prepare).replaceAll("[0]*$", "").replaceAll(".$", "");

       // return formatter.format(round(value));
    }
}
