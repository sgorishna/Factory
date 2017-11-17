package com.spec.controllers;

import com.spec.model.Users;
import com.spec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Svetik on 16/10/2017.
 */
@Controller
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "admin/activateUser", method = RequestMethod.GET)
    public String activateAccount(@ModelAttribute("username") String username) {

        userService.activate(username);

        return "redirect:home";

    }

    @RequestMapping(value = "admin/deactivateUser", method = RequestMethod.GET)
    public String deactivateAccount(@ModelAttribute("username") String username) {

        userService.deactivate(username);

        return "redirect:home";

    }

    @RequestMapping(value = "admin/deleteUser", method = RequestMethod.GET)
    public String deleteAccount(@ModelAttribute("username") String username) {

        Users user = userService.findByLogin(username).get(0);

        userService.delete(user);

        return "redirect:home";

    }

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String users(Model model) {
//
//
//        model.addAttribute("users", userService.findAll());
//        return "admin/userList";
//
//    }
}
