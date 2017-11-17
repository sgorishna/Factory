package com.spec.controllers;

import com.spec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.spec.utils.WebappConstants.CONTEXT;
import static com.spec.utils.WebappConstants.CURRENT_SESSION_ACCOUNT;

/**
 * Created by Svetik on 14/09/2017.
 */

@Controller

public class LoginController {


    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;

    }


    //Spring Security see this :
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(
            @ModelAttribute("j_username") String username, HttpServletRequest request) {

       /* Users u = userService.findByLogin(username).get(0);

        request.getSession().setAttribute(CURRENT_SESSION_ACCOUNT, u);*/

        return "login";

    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(HttpServletRequest request, Authentication authentication) {


        request.getSession().setAttribute("users", userService.findAll());
        request.getSession().setAttribute(CONTEXT, request.getContextPath()+"/admin");
        request.getSession().setAttribute(CURRENT_SESSION_ACCOUNT,authentication.getName());
        return "admin/home";
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String userHome(HttpServletRequest request) {

        request.getSession().setAttribute(CONTEXT, request.getContextPath()+"/user");
        return "user/home";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {

        return "redirect:login";
    }

    @RequestMapping("/invalidCredentials")
    public String invalidCredentials(RedirectAttributes ra) {

        ra.addFlashAttribute("error", "Invalid credentials!");
        return "redirect:login";
    }


}
