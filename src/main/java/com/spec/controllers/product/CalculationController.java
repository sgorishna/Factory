package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.model.Product;
import com.spec.utils.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Svetik on 04/10/2017.
 */
@Controller
public class CalculationController extends AbstractController {


    private final Calculation calculation;

    @Autowired
    public CalculationController(Calculation calculation) {
        this.calculation = calculation;
    }


    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String compoundCompoundList(@RequestParam(value = "id") int id, Model model) {

        Product product = calculation.getInit().getProductService().findById(id);

        calculation.finishedProductPercentage(product);


        model.addAttribute("percentage", calculation.getResult());
        model.addAttribute("product", product.getName());

        return "product/success";

    }
}
