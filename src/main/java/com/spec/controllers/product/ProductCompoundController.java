package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;
import com.spec.model.ProductCompound;
import com.spec.service.CompoundService;
import com.spec.service.ProductCompoundService;
import com.spec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Svetik on 23/09/2017.
 */
@Controller
public class ProductCompoundController extends AbstractController {

    private final ProductCompoundService service;

    private final ProductService productService;

    private final CompoundService compoundService;

    @Autowired
    public ProductCompoundController(ProductCompoundService service, ProductService productService, CompoundService compoundService) {
        this.service = service;
        this.productService = productService;
        this.compoundService = compoundService;
    }

    @RequestMapping(value = "/productCompoundList", method = RequestMethod.GET)
    public String productCompoundList(@RequestParam(value = "id") int id, Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        List<ProductCompound> compounds = product.getCompoundsListByIdProduct();

        model.addAttribute("compounds", compounds);

        return "product/productCompoundList";

    }

    @RequestMapping(value = "/newProductCompound", method = RequestMethod.GET)
    public String newProductCompound(@RequestParam(value = "id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/newProductCompound";

    }

    @RequestMapping(value = "/newProductCompound", method = RequestMethod.POST)
    public String newProductCompound(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "percentage") double percentage, @RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {

        ProductCompound productCompound = new ProductCompound();

        if (!code.equals("N/A")) {

            productCompound.setCompound(compoundService.findByCode(code).get(0));
            productCompound.setProduct(productService.findById(id));
            productCompound.setCompoundPercentage(percentage);

            try {
                service.save(productCompound);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newProductCompound?id=" + id;
            }


        } else if (!compoundService.findByName(name).isEmpty()) {

            productCompound.setCompound(compoundService.findByName(name).get(0));
            productCompound.setProduct(productService.findById(id));
            productCompound.setCompoundPercentage(percentage);

            try {
                service.save(productCompound);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newProductCompound?id=" + id;
            }


        } else {

            redirectAttributes.addFlashAttribute("errMsg", "compound '" + name + "' does not exist in system");

            return "redirect:newProductCompound?id=" + id;

        }
        return "redirect:productCompoundList?id=" + id;

    }


    @RequestMapping(value = "/updateProductCompound", method = RequestMethod.GET)
    public String updateProductCompound(@RequestParam(value = "id") int id, Model model) {

        ProductCompound compound = service.findById(id);


        model.addAttribute("compound", compound);


        return "product/updateProductCompound";


    }

    @RequestMapping(value = "/updateProductCompound", method = RequestMethod.POST)
    public String updateProductCompound(@ModelAttribute("compound") ProductCompound compound) {

        ProductCompound cmp = service.findById(compound.getId());

        cmp.setCompoundPercentage(compound.getCompoundPercentage());

        service.update(cmp);


        return "redirect:productCompoundList?id=" + cmp.getProduct().getId();


    }

    @RequestMapping(value = "/deleteProductCompound", method = RequestMethod.GET)
    public String deleteProductCompound(@RequestParam(value = "id") int id) {

        ProductCompound compound = service.findById(id);

        service.delete(compound);

        return "redirect:productCompoundList?id=" + compound.getProduct().getId();
    }


}
