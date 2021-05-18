package ru.mirea.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.fitness.models.TrainingProgram;
import ru.mirea.fitness.models.User;
import ru.mirea.fitness.repo.TrainingProgramRepository;
import ru.mirea.fitness.repo.UserRepository;

@Controller
public class MainController {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserRepository userRepository;

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
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        userRepository.save(user);

        return "edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("title", "Fitness - Edit");
        return "edit";
    }

}
