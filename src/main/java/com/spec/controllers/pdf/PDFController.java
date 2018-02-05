package com.spec.controllers.pdf;

import com.spec.controllers.AbstractController;
import com.spec.utils.WebappConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Svetik on 26/11/2017.
 */
@Controller
public class PDFController extends AbstractController {

    @RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
    public
    String makePdf(@RequestParam(name = "data") String data, Model model) {

        WebappConstants.declarationResult = data;
        model.addAttribute("list", data);
        model.addAttribute("result", WebappConstants.resultList);
        model.addAttribute("declaration", WebappConstants.declarationResult);
        model.addAttribute("total", WebappConstants.total);
        model.addAttribute("legalName", WebappConstants.legalName);


        return "pdfView";
    }
}
