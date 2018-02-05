package com.spec.controllers.compound;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.service.CompoundService;
import com.spec.utils.Composition;
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

public class CompoundController extends AbstractController {

    private final CompoundService compoundService;

    private final Composition composition;


    @Autowired
    public CompoundController(CompoundService compoundService, Composition composition) {
        this.compoundService = compoundService;

        this.composition = composition;
    }

    @RequestMapping(value = "/compounds", method = RequestMethod.GET)
    public String compounds(Model model) {


        model.addAttribute("compounds", compoundService.findAllASC());
        return "compound/compoundList";

    }


    @RequestMapping(value = "/newCompound", method = RequestMethod.GET)
    public String newPCompound(Model model) {

        model.addAttribute("compound", new Compound());
        return "compound/newCompound";

    }

    @RequestMapping(value = "/newCompound", method = RequestMethod.POST)
    public String newCompound(@ModelAttribute("compound") Compound compound, RedirectAttributes redirectAttributes) {
        try {

            compoundService.save(compound);
            return "redirect:compounds";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:newCompound";
        }

    }

    @RequestMapping(value = "/updateCompound", method = RequestMethod.GET)
    public String updateCompound(@RequestParam(value = "id") int id, Model model) {

        Compound compound = compoundService.findById(id);
        model.addAttribute("compound", compound);


        return "compound/updateCompound";


    }

    @RequestMapping(value = "/updateCompound", method = RequestMethod.POST)
    public String updateCompound(@ModelAttribute("compound") Compound compound, RedirectAttributes redirectAttributes) {

        try {
            compoundService.update(compound);
            return "redirect:compounds";

        } catch (InvalidDataException e) {


            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());


            return "redirect:updateCompound?id=" + compound.getId();
        }


    }


    @RequestMapping(value = "/checkCompoundName", method = RequestMethod.GET)
    public String checkCompoundName(@RequestParam(value = "name") String name, Model model) {

        if (!compoundService.findByName(name).isEmpty()) {

            model.addAttribute("taken", "Name already exists in system");
        } else {
            model.addAttribute("available", "Available");
        }


        return "compound/checkName";

    }

    @RequestMapping(value = "/checkCompoundCode", method = RequestMethod.GET)
    public String checkCompoundCode(@RequestParam(value = "code") String code, Model model) {

        if(!code.equals("")) {

            if (!compoundService.findByCode(code).isEmpty()) {

                model.addAttribute("taken", "Code already exists in system");
            } else {
                model.addAttribute("available", "Available");
            }

        }
        return "compound/checkCode";

    }

    @RequestMapping(value = "/findCodeByCompoundName", method = RequestMethod.GET)
    public String findCodeByCompoundName(@RequestParam(value = "name") String name, Model model) {

        List<Compound> с = compoundService.findByName(name);


        if (!с.isEmpty()) {
            String code = с.get(0).getCode();

            if (null != code) {


                model.addAttribute("code", code);
            } else model.addAttribute("no_code", "NO CODE");
        } else {
            model.addAttribute("no_code", "NO CODE");
        }


        return "compound/findCode";

    }

    @RequestMapping(value = "/deleteCompound", method = RequestMethod.GET)
    public String deleteCompound(@RequestParam(value = "id") int id) {

        compoundService.delete(compoundService.findById(id));

        return "redirect:compounds";
    }

    @RequestMapping(value = "/compoundAutocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List<String> compoundAutocomplete(@RequestParam("term") String name) {

        return compoundService.autocompleteCompoundName(name);

    }

    @RequestMapping(value = "/compoundComposition", method = RequestMethod.GET)
    public String compoundComposition(@RequestParam(value = "id") int id, Model model) {

        Compound compound = compoundService.findById(id);

        composition.compoundComposition(compound);

        model.addAttribute("composition", composition.getResult());
        model.addAttribute("compound", compound);

        return "compound/compoundComposition";

    }
}
