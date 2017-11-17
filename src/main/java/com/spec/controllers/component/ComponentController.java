package com.spec.controllers.component;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Component;
import com.spec.service.ComponentService;
import com.spec.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Created by Svetik on 17/09/2017.
 */
@Controller

public class ComponentController extends AbstractController {

    private final ComponentService componentService;


    @Autowired
    private Utils utils;


    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;

    }


    @RequestMapping(value = "/components", method = RequestMethod.GET)
    public String components(Model model) {


        model.addAttribute("components", componentService.findAll());
        return "component/componentList";

    }


    @RequestMapping(value = "/newComponent", method = RequestMethod.GET)
    public String newComponent(Model model) {

        model.addAttribute("component", new Component());
        return "component/newComponent";

    }

    @RequestMapping(value = "/newComponent", method = RequestMethod.POST)
    public String newComponent(@ModelAttribute("component") Component component, RedirectAttributes redirectAttributes) {
        try {

            componentService.save(component);
            return "redirect:components";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:newComponent";
        }

    }

    @RequestMapping(value = "/renameComponent", method = RequestMethod.GET)
    public String renameComponent(@RequestParam(value = "id") int id, Model model) {

        Component component = componentService.findById(id);
        model.addAttribute("component", component);


        return "component/renameComponent";


    }

    @RequestMapping(value = "/renameComponent", method = RequestMethod.POST)
    public String renameComponent(@ModelAttribute("component") Component component, RedirectAttributes redirectAttributes) {

        try {
            componentService.update(component);
            return "redirect:components";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:renameComponent?id=" + component.getId();
        }


    }


    @RequestMapping(value = "/checkComponentName", method = RequestMethod.GET)
    public String checkComponentName(@RequestParam(value = "name") String name, Model model) {

        if (!componentService.findByName(name).isEmpty()) {

            model.addAttribute("taken", "Name already exists in system");
        } else {
            model.addAttribute("available", "Available");
        }


        return "component/checkName";

    }

    @RequestMapping(value = "/deleteComponent", method = RequestMethod.GET)
    public String deleteComponent(@RequestParam(value = "id") int id) {

        componentService.delete(componentService.findById(id));

        return "redirect:components";
    }


    @RequestMapping(value = "/componentAutocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List<String> componentAutocomplete(@RequestParam("term") String name) {

        return componentService.autocompleteComponentName(name);

    }
}
