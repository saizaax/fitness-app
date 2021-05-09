package ru.mirea.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.fitness.models.TrainingProgram;
import ru.mirea.fitness.repo.TrainingProgramRepository;

@Controller
public class MainController {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll();
        model.addAttribute("title", "Fitness - Dashboard");
        model.addAttribute("trainingPrograms", trainingPrograms);
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
