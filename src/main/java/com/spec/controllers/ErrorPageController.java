package com.spec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Svetik on 30/10/2017.
 */
@Controller
public class ErrorPageController  {

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accesssDenied() {


        return "errors/403";

    }
}
