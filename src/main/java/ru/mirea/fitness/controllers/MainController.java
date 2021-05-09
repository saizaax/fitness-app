package ru.mirea.fitness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Fitness - Dashboard");
        return "index";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("title", "Fitness - Authorization");
        return "authorization";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("title", "Fitness - Edit");
        return "edit";
    }

}
