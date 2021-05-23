package ru.mirea.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.fitness.models.TrainingProgram;
import ru.mirea.fitness.models.User;
import ru.mirea.fitness.repo.TrainingProgramRepository;
import ru.mirea.fitness.repo.UserRepository;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/payment")
    public String payment(Model model, Principal principal) {
        model.addAttribute("title", "Fitness - Subscription Payment");
        model.addAttribute("user", userRepository.getUserByUsername(principal.getName()));
        model.addAttribute("dateStart", userRepository.getUserByUsername(principal.getName()).getSubscriptionEnd());
        return "payment";
    }

    @PostMapping("/process_payment")
    public String processPayment(User user, Principal principal) {
        User currentUser = userRepository.getUserByUsername(principal.getName());
        currentUser.setSubscriptionEnd(user.getSubscriptionEnd());
        currentUser.setPrice(user.getPrice());
        int days = Integer.parseInt(currentUser.getDays()) + Integer.parseInt(user.getDays());
        currentUser.setDays(Integer.toString(days));
        userRepository.save(currentUser);
        return "redirect:/";
    }

    @PostMapping("/edit_user")
    public String editUser(User user) {
        if (user.getPassword().length() != 60) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit_program/{id}")
    public String editProgram(@PathVariable String id, Model model) {
        if (id.equals("new")) {
            model.addAttribute("title", "Fitness - New Training Program");
            model.addAttribute("trainingProgram", new TrainingProgram());
            model.addAttribute("endpoint", "/new_program");
        }
        else {
            TrainingProgram trainingProgram = trainingProgramRepository.getTrainingProgramById(Long.parseLong(id));
            model.addAttribute("title", "Fitness - Edit Training Program");
            model.addAttribute("trainingProgram", trainingProgram);
            model.addAttribute("endpoint", "/edit_program");
        }
        return "edit_program";
    }

    @PostMapping("/new_program/{id}")
    public String processNewProgram(TrainingProgram trainingProgram) {
        trainingProgramRepository.save(trainingProgram);
        return "redirect:/";
    }

    @PostMapping("/edit_program/{id}")
    public String processProgram(@PathVariable String id, TrainingProgram trainingProgram) {
        TrainingProgram prev = trainingProgramRepository.getTrainingProgramById(Long.parseLong(id));
        trainingProgram.setId(prev.getId());
        trainingProgramRepository.save(trainingProgram);
        return "redirect:/";
    }

    @PostMapping("/subscription_add/{id}")
    public String subscriptionAdd(@PathVariable String id, Principal principal) {
        User currentUser = userRepository.getUserByUsername(principal.getName());
        Set<TrainingProgram> trainingPrograms = currentUser.getTrainingPrograms();
        TrainingProgram trainingProgram = trainingProgramRepository.getTrainingProgramById(Long.parseLong(id));
        trainingPrograms.add(trainingProgram);
        userRepository.save(currentUser);
        return "redirect:/";
    }

    @PostMapping("/subscription_remove/{id}")
    public String subscriptionRemove(@PathVariable String id, Principal principal) {
        User currentUser = userRepository.getUserByUsername(principal.getName());
        Set<TrainingProgram> trainingPrograms = currentUser.getTrainingPrograms();
        TrainingProgram trainingProgram = trainingProgramRepository.getTrainingProgramById(Long.parseLong(id));
        trainingPrograms.remove(trainingProgram);
        userRepository.save(currentUser);
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
