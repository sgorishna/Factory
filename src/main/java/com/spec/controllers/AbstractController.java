package com.spec.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.spec.utils.WebappConstants.CONTEXT;

/**
 * Created by Svetik on 29/10/2017.
 */
@RequestMapping(value = "{context}")
public abstract class AbstractController {


    final void setContext(@PathVariable("context") String context, HttpServletRequest request) {

        context = (String) request.getSession().getAttribute(CONTEXT);

    }



}
