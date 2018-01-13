package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;
import com.spec.service.ProductService;
import com.spec.utils.Composition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Svetik on 17/09/2017.
 */
@Controller
public class ProductController extends AbstractController {

    private final ProductService productService;

    private final Composition composition;

    @Autowired
    public ProductController(ProductService productService, Composition composition) {
        this.productService = productService;
        this.composition = composition;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model){

        model.addAttribute("products",productService.findAll());
        return "product/productList";

    }


    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)
    public String newProduct(Model model){

        model.addAttribute("product",new Product());
        return "product/newProduct";

    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    public String newProduct(@ModelAttribute("product")Product product, RedirectAttributes redirectAttributes){
        try {

productService.save(product);
        return "redirect:products";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg",e.getMessage());


            return "redirect:newProduct";
        }

    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
    public String updateProduct(@RequestParam(value = "id") int id, Model model){

        Product product = productService.findById(id);
       model.addAttribute("product",product);

        System.out.println(product.getId());
     return "product/updateProduct";

    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product, Model model, RedirectAttributes redirectAttributes){

        try {
            productService.update(product);
            return "redirect:products";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg",e.getMessage());


            return "redirect:updateProduct?id="+product.getId();
        }



    }


    @RequestMapping(value="/checkProductName", method = RequestMethod.GET)
    public String checkProductName(@RequestParam(value="name") String name, Model model){

        if (!productService.findByName(name).isEmpty()) {

            model.addAttribute("taken", "Product already exists in system");
        } else {
            model.addAttribute("available", "Available");
        }



        return "product/checkName";

    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam(value = "id") int id){

        productService.delete(productService.findById(id));

        return "redirect:products";
    }

    @RequestMapping(value = "/productComposition", method = RequestMethod.GET)
    public String productComposition(@RequestParam(value = "id") int id, Model model){

        Product product = productService.findById(id);

        composition.productComposition(product);

        model.addAttribute("composition", composition.getResult());
        model.addAttribute("product", product);

        return "product/productComposition";

    }



    @RequestMapping(value = "/checkProductCode", method = RequestMethod.GET)
    public String checkProductCode(@RequestParam(value = "code") String code, Model model) {


        if (!productService.findByCode(code).isEmpty()) {

            model.addAttribute("taken", "Code already exists in system");
        } else {
            model.addAttribute("available", "Available");
        }


        return "product/checkCode";

    }
}
