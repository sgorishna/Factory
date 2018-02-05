package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.model.Product;
import com.spec.model.Quid;
import com.spec.model.Result;
import com.spec.service.QuidService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Svetik on 04/10/2017.
 */
@Controller
public class CalculationController extends AbstractController {


    private final Calculation calculation;


    private final Total total;

    private final QuidService quidService;

    @Autowired
    public CalculationController(Calculation calculation, Total total, QuidService quidService) {
        this.calculation = calculation;
        this.total = total;
        this.quidService = quidService;
    }


    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String compoundCompoundList(@RequestParam(value = "id") int id, Model model) {



        WebappConstants.allFunctions = null;

        WebappConstants.declarationResult = "";
        WebappConstants.total = "";
        WebappConstants.legalName = "";
//
        List<String> quidded = Collections.EMPTY_LIST;
        List<String> declared = Collections.EMPTY_LIST;
        List<String> allergen = Collections.EMPTY_LIST;
        List<String> function = Collections.EMPTY_LIST;

        String declaration= "";

        String declarationEditable ="";

        List<Quid> quid = quidService.findByProductId(id);

        if (!quid.isEmpty()) {

            quidded = Arrays.asList(quid.get(0).getQuidded().split(","));
            declared = Arrays.asList(quid.get(0).getDeclared().split(","));
            allergen = Arrays.asList(quid.get(0).getAllergen().split(","));
            function = Arrays.asList(quid.get(0).getFunction().split(","));

            declaration = quid.get(0).getDeclaration();
            declarationEditable = quid.get(0).getDeclarationEditable();

        }

        Product product = calculation.getInit().getProductService().findById(id);

        calculation.finishedProductPercentage(product);

        List<Result> res = calculation.getResult();

        total.total(res);

       String salt = total.brineTotal("salt", res);

        System.out.println("salt" + salt);

        String water = total.brineTotal("water", res);

        System.out.println("water" + water);

        WebappConstants.total = getTotalSum(res);
        WebappConstants.legalName = product.getLegalName();

        WebappConstants.resultList = res;


        model.addAttribute("percentage", res);

        model.addAttribute("product", product.getName());
        model.addAttribute("total", getTotalSum(res));

        model.addAttribute("quidded", quidded);
        model.addAttribute("declared", declared);
        model.addAttribute("allergen", allergen);
        model.addAttribute("function", function);

        model.addAttribute("allergenList", WebappConstants.allergens);
        model.addAttribute("functionList", WebappConstants.functions);

        model.addAttribute("declaration", declaration);
        model.addAttribute("declarationEditable", declarationEditable);

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
