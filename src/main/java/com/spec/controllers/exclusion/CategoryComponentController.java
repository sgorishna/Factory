package com.spec.controllers.exclusion;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Category;
import com.spec.model.CategoryComponent;
import com.spec.service.CategoryComponentService;
import com.spec.service.CategoryService;
import com.spec.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Svetik on 23/09/2017.
 */
@Controller
public class CategoryComponentController extends AbstractController {

    private final CategoryComponentService service;

    private final CategoryService categoryService;

    private final ComponentService componentService;

    @Autowired
    public CategoryComponentController(CategoryComponentService service, CategoryService categoryService, ComponentService componentService) {
        this.service = service;
        this.categoryService = categoryService;
        this.componentService = componentService;
    }

    @RequestMapping(value = "/categoryException", method = RequestMethod.GET)
    public String categoryException(@RequestParam(value = "id") int id, Model model) {


        List<CategoryComponent> components = categoryService.findById(id).getComponentsByIdCategory();


        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("components", components);


        return "exclusion/categoryException";


    }

    @RequestMapping(value = "/newCategoryComponent", method = RequestMethod.GET)
    public String newCategoryComponent(@RequestParam(value = "id") int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "exclusion/newCategoryComponent";

    }

    @RequestMapping(value = "/newCategoryComponent", method = RequestMethod.POST)
    public String newCategoryComponent(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, RedirectAttributes redirectAttributes) {

        CategoryComponent categoryComponent = new CategoryComponent();

        if (!componentService.findByName(name).isEmpty()) {
            categoryComponent.setCategory(categoryService.findById(id));
            categoryComponent.setComponent(componentService.findByName(name).get(0));


            try {
                service.save(categoryComponent);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newCategoryComponent?id=" + id;
            }


        } else {

            redirectAttributes.addFlashAttribute("errMsg", "component '" + name + "' does not exist in system");

            return "redirect:newCategoryComponent?id=" + id;

        }
        return "redirect:categoryException?id=" + id;

    }


    @RequestMapping(value = "/deleteCategoryComponent", method = RequestMethod.GET)
    public String deleteCategoryComponent(@RequestParam(value = "id") int id) {

        CategoryComponent category = service.findById(id);

        service.delete(category);

        return "redirect:categoryException?id=" + category.getCategory().getId();
    }


}