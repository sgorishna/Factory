package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.model.Product;
import com.spec.model.Result;
import com.spec.utils.Calculation;
import com.spec.utils.Total;
import com.spec.utils.WebappConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Svetik on 04/10/2017.
 */
@Controller
public class CalculationController extends AbstractController {


    private final Calculation calculation;

    private final Total total;

    @Autowired
    public CalculationController(Calculation calculation, Total total) {
        this.calculation = calculation;
        this.total = total;
    }


    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String compoundCompoundList(@RequestParam(value = "id") int id, Model model) {

        WebappConstants.allQuid = null;

        WebappConstants.allDeclared = null;

        WebappConstants.allAllergens = null;

        WebappConstants.allFunctions = null;

        WebappConstants.declarationResult = "";
        WebappConstants.total = "";
        WebappConstants.legalName = "";


        Product product = calculation.getInit().getProductService().findById(id);

        calculation.finishedProductPercentage(product);

        List<Result> res = calculation.getResult();

        total.total(res);

        WebappConstants.total = getTotalSum(res);
        WebappConstants.legalName = product.getLegalName();

        WebappConstants.resultList = res;


        model.addAttribute("percentage", res);
        model.addAttribute("product", product.getName());
        model.addAttribute("total", getTotalSum(res));

        model.addAttribute("allergens", WebappConstants.allergens);
        model.addAttribute("functions", WebappConstants.functions);

        return "product/success";

    }

    private String getTotalSum(List<Result> res) {

        double total = 0.00;

        DecimalFormat df = new DecimalFormat("#.##");

        for (Result result : res) {

            if (result.getTotal() != "") {

                total += Double.parseDouble(result.getTotal());
            }

        }

        return String.valueOf(Double.valueOf(df.format(total)));
    }
}
