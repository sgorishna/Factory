package com.spec.controllers.compound;

import com.spec.controllers.AbstractController;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Compound;
import com.spec.model.CompoundCompound;
import com.spec.service.CompoundCompoundService;
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
public class CompoundCompoundController extends AbstractController {

    private final CompoundCompoundService service;

    private final CompoundService compoundService;


    @Autowired
    public CompoundCompoundController(CompoundCompoundService service, CompoundService compoundService) {
        this.service = service;
        this.compoundService = compoundService;

    }

    @RequestMapping(value = "/compoundCompoundList", method = RequestMethod.GET)
    public String compoundCompoundList(@RequestParam(value = "id") int id, Model model) {

        Compound compound = compoundService.findById(id);

        model.addAttribute("compound", compound);

        List<CompoundCompound> child = compound.getChildByParentId();

        model.addAttribute("child", child);

        return "compound/compoundCompoundList";

    }

    @RequestMapping(value = "/newCompoundCompound", method = RequestMethod.GET)
    public String newCompoundCompound(@RequestParam(value = "id") int id, Model model) {
        Compound compound = compoundService.findById(id);
        model.addAttribute("compound", compound);
        return "compound/newCompoundCompound";

    }

    @RequestMapping(value = "/newCompoundCompound", method = RequestMethod.POST)
    public String newCompoundCompound(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "percentage") double percentage, @RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {

        CompoundCompound compoundCompound = new CompoundCompound();

        if (!code.equals("N/A")) {

            compoundCompound.setParent(compoundService.findById(id));//setCompound(compoundService.findById(id));
            compoundCompound.setChild(compoundService.findByCode(code).get(0));
            compoundCompound.setChildPercentage(percentage);

            try {
                service.save(compoundCompound);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newCompoundCompound?id=" + id;
            }


        } else if (!compoundService.findByName(name).isEmpty()) {
            compoundCompound.setParent(compoundService.findById(id));//setCompound(compoundService.findById(id));
            compoundCompound.setChild(compoundService.findByName(name).get(0));
            compoundCompound.setChildPercentage(percentage);

            try {
                service.save(compoundCompound);
            } catch (InvalidDataException e) {
                redirectAttributes.addFlashAttribute("errMsg", e.getMessage());

                return "redirect:newCompoundCompound?id=" + id;
            }


        } else {

            redirectAttributes.addFlashAttribute("errMsg", "compound '" + name + "' does not exist in system");

            return "redirect:newCompoundCompound?id=" + id;

        }
        return "redirect:compoundCompoundList?id=" + id;

    }


    @RequestMapping(value = "/updateCompoundCompound", method = RequestMethod.GET)
    public String updateCompoundCompound(@RequestParam(value = "id") int id, Model model) {

        CompoundCompound child = service.findById(id);


        model.addAttribute("child", child);


        return "compound/updateCompoundCompound";


    }

    @RequestMapping(value = "/updateCompoundCompound", method = RequestMethod.POST)
    public String updateCompoundCompound(@ModelAttribute("child") CompoundCompound child) {

        CompoundCompound cmp = service.findById(child.getId());

        cmp.setChildPercentage(child.getChildPercentage());

        service.update(cmp);


        return "redirect:compoundCompoundList?id=" + cmp.getParent().getId();


    }

    @RequestMapping(value = "/deleteCompoundCompound", method = RequestMethod.GET)
    public String deleteCompoundComponent(@RequestParam(value = "id") int id) {

        CompoundCompound compound = service.findById(id);

        service.delete(compound);

        return "redirect:compoundCompoundList?id=" + compound.getParent().getId();
    }


}