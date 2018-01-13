package com.spec.controllers.exclusion;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Category;
import com.spec.service.CategoryService;
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
 * Created by Svetik on 17/09/2017.
 */
@Controller

public class CategoryController extends AbstractController {

    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }


    @RequestMapping(value = "/exceptions", method = RequestMethod.GET)
    public String categories(Model model) {


        model.addAttribute("category", categoryService.findAll());
        return "exclusion/categoryList";

    }


    @RequestMapping(value = "/newCategory", method = RequestMethod.GET)
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());
        return "exclusion/newCategory";

    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
    public String newCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        try {

            categoryService.save(category);
            return "redirect:exceptions";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:newCategory";
        }

    }

    @RequestMapping(value = "/renameCategory", method = RequestMethod.GET)
    public String renameCategory(@RequestParam(value = "id") int id, Model model) {


        Category category = categoryService.findById(id);
        model.addAttribute("category", category);


        return "exclusion/renameCategory";


    }

    @RequestMapping(value = "/renameCategory", method = RequestMethod.POST)
    public String renameCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {

        try {
            categoryService.update(category);
            return "redirect:exceptions";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:renameCategory?id=" + category.getId();
        }


    }


    @RequestMapping(value = "/checkCategoryName", method = RequestMethod.GET)
    public String checkCategoryName(@RequestParam(value = "name") String name, Model model) {

        List<Category> с = categoryService.findByName(name);

        if (!categoryService.findByName(name).isEmpty()) {

            model.addAttribute("taken", "Name already exists in system");
        } else {
            model.addAttribute("available", "Available");
        }


        return "exclusion/checkName";

    }



    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam(value = "id") int id) {

        categoryService.delete(categoryService.findById(id));

        return "redirect:exceptions";
    }


}
