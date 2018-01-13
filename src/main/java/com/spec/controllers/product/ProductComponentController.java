package com.spec.controllers.product;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;
import com.spec.model.ProductComponent;
import com.spec.service.ComponentService;
import com.spec.service.ProductComponentService;
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
public class ProductComponentController extends AbstractController {

    private final ProductComponentService service;

    private final ProductService productService;

    private final ComponentService componentService;

    @Autowired
    public ProductComponentController(ProductComponentService service, ProductService productService, ComponentService componentService) {
        this.service = service;
        this.productService = productService;
        this.componentService = componentService;
    }

    @RequestMapping(value = "/productComponentList", method = RequestMethod.GET)
    public String productComponentList(@RequestParam(value = "id") int id, Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        List<ProductComponent> components = product.getComponentsListByIdProduct();

        model.addAttribute("components", components);

        return "product/productComponentList";

    }

    @RequestMapping(value = "/newProductComponent", method = RequestMethod.GET)
    public String newProductComponent(@RequestParam(value = "id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/newProductComponent";

    }

    @RequestMapping(value = "/newProductComponent", method = RequestMethod.POST)
    public String newProductComponent(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "percentage") double percentage, @RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {

        ProductComponent productComponent = new ProductComponent();

        if (!code.equals("N/A")) {

            productComponent.setComponent(componentService.findByCode(code).get(0));
            productComponent.setProduct(productService.findById(id));
            productComponent.setComponentPercentage(percentage);

            try {
                service.save(productComponent);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newProductComponent?id=" + id;
            }



        }

       else if (!componentService.findByName(name).isEmpty()) {

            productComponent.setComponent(componentService.findByName(name).get(0));
            productComponent.setProduct(productService.findById(id));
            productComponent.setComponentPercentage(percentage);

            try {
                service.save(productComponent);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newProductComponent?id=" + id;
            }



        } else {

            redirectAttributes.addFlashAttribute("errMsg", "component '" + name + "' does not exist in system");

            return "redirect:newProductComponent?id=" + id;

        }
        return "redirect:productComponentList?id=" + id;

    }


    @RequestMapping(value = "/updateProductComponent", method = RequestMethod.GET)
    public String updateProductComponent(@RequestParam(value = "id") int id, Model model) {

        ProductComponent component = service.findById(id);


        model.addAttribute("component", component);


        return "product/updateProductComponent";


    }

    @RequestMapping(value = "/updateProductComponent", method = RequestMethod.POST)
    public String updateProductComponent(@ModelAttribute("component") ProductComponent component) {

        ProductComponent cmp = service.findById(component.getId());

        cmp.setComponentPercentage(component.getComponentPercentage());

        service.update(cmp);


        return "redirect:productComponentList?id=" + cmp.getProduct().getId();


    }

    @RequestMapping(value = "/deleteProductComponent", method = RequestMethod.GET)
    public String deleteProductComponent(@RequestParam(value = "id") int id) {

        ProductComponent component = service.findById(id);

        service.delete(component);

        return "redirect:productComponentList?id=" + component.getProduct().getId();
    }


}
