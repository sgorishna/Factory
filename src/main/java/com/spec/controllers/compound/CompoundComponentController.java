package com.spec.controllers.compound;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.model.CompoundComponent;
import com.spec.service.ComponentService;
import com.spec.service.CompoundComponentService;
import com.spec.service.CompoundService;
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
public class CompoundComponentController extends AbstractController {

    private final CompoundComponentService service;

    private final CompoundService compoundService;

    private final ComponentService componentService;

    @Autowired
    public CompoundComponentController(CompoundComponentService service, CompoundService compoundService, ComponentService componentService) {
        this.service = service;
        this.compoundService = compoundService;
        this.componentService = componentService;
    }

    @RequestMapping(value = "/compoundComponentList", method = RequestMethod.GET)
    public String compoundComponentList(@RequestParam(value = "id") int id, Model model) {

        Compound compound = compoundService.findById(id);

        model.addAttribute("compound", compound);

        List<CompoundComponent> components = compound.getComponentListByIdCompound();

        model.addAttribute("components", components);

        return "compound/compoundComponentList";

    }

    @RequestMapping(value = "/newCompoundComponent", method = RequestMethod.GET)
    public String newCompoundComponent(@RequestParam(value = "id") int id, Model model) {
        Compound compound = compoundService.findById(id);
        model.addAttribute("compound", compound);
        return "compound/newCompoundComponent";

    }

    @RequestMapping(value = "/newCompoundComponent", method = RequestMethod.POST)
    public String newCompoundComponent(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "percentage") double percentage,  @RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {

        CompoundComponent compoundComponent = new CompoundComponent();

        if (!code.equals("N/A")) {

            compoundComponent.setCompound(compoundService.findById(id));
            compoundComponent.setComponent(componentService.findByCode(code).get(0));
            compoundComponent.setComponentPercentage(percentage);

            try {
                service.save(compoundComponent);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newCompoundCompound?id=" + id;
            }

        } else  if (!componentService.findByName(name).isEmpty()) {
            compoundComponent.setCompound(compoundService.findById(id));
            compoundComponent.setComponent(componentService.findByName(name).get(0));
            compoundComponent.setComponentPercentage(percentage);

            try {
                service.save(compoundComponent);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newCompoundComponent?id=" + id;
            }



        } else {

            redirectAttributes.addFlashAttribute("errMsg", "component '" + name + "' does not exist in system");

            return "redirect:newCompoundComponent?id=" + id;

        }
        return "redirect:compoundComponentList?id=" + id;

    }


    @RequestMapping(value = "/updateCompoundComponent", method = RequestMethod.GET)
    public String updateCompoundComponent(@RequestParam(value = "id") int id, Model model) {

        CompoundComponent component = service.findById(id);


        model.addAttribute("component", component);


        return "compound/updateCompoundComponent";


    }

    @RequestMapping(value = "/updateCompoundComponent", method = RequestMethod.POST)
    public String updateCompoundComponent(@ModelAttribute("component") CompoundComponent component) {

        CompoundComponent cmp = service.findById(component.getId());

        cmp.setComponentPercentage(component.getComponentPercentage());

        service.update(cmp);


        return "redirect:compoundComponentList?id=" + cmp.getCompound().getId();


    }

    @RequestMapping(value = "/deleteCompoundComponent", method = RequestMethod.GET)
    public String deleteCompoundComponent(@RequestParam(value = "id") int id) {

        CompoundComponent component = service.findById(id);

        service.delete(component);

        return "redirect:compoundComponentList?id=" + component.getCompound().getId();
    }


}