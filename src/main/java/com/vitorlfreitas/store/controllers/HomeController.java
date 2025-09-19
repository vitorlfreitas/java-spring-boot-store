package com.vitorlfreitas.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// A bean is an object managed by Spring
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute(
                "name",
                "Vitor");

        return "index";
    }

}
