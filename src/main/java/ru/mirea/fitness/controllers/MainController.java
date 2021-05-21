package ru.mirea.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.fitness.models.TrainingProgram;
import ru.mirea.fitness.models.User;
import ru.mirea.fitness.repo.TrainingProgramRepository;
import ru.mirea.fitness.repo.UserRepository;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().iterator().next().toString().equals("ROLE_ADMIN")) {
            Iterable<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll();
            Iterable<User> trainingUsers = userRepository.findAll();
            model.addAttribute("title", "Fitness - Admin Dashboard");
            model.addAttribute("trainingPrograms", trainingPrograms);
            model.addAttribute("trainingUsers", trainingUsers);
            model.addAttribute("user", userRepository.getUserByUsername(principal.getName()));
            return "dashboard";
        }

        Iterable<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll();
        model.addAttribute("title", "Fitness - Dashboard");
        model.addAttribute("trainingPrograms", trainingPrograms);
        model.addAttribute("user", userRepository.getUserByUsername(principal.getName()));
        return "index";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("title", "Fitness - Authorization");
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/edit_user")
    public String editUser(User user) {
        User prev = userRepository.getUserByUsername(user.getUsername());

        if (user.getPassword().length() != 60) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        user.setId(prev.getId());
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "redirect:/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal) {
        model.addAttribute("title", "Fitness - Edit");
        model.addAttribute("user", userRepository.getUserByUsername(principal.getName()));
        return "edit";
    }

    @PostMapping("/edit_profile")
    public String editData(User user, Principal principal) {
        User prev = userRepository.getUserByUsername(principal.getName());

        prev.setAvatarUrl(user.getAvatarUrl());
        prev.setFirstName(user.getFirstName());
        prev.setSecondName(user.getSecondName());
        prev.setAge(user.getAge());
        prev.setHeight(user.getHeight());
        prev.setWeight(user.getWeight());
        prev.setVisits(user.getVisits());
        prev.setHours(user.getHours());

        userRepository.save(prev);
        return "redirect:/";
    }
}
