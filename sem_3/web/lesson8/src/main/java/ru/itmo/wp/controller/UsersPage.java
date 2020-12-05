package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }
    @PostMapping("/users/disable")
    public String disable(@ModelAttribute("registerForm") UserCredentials disableForm,
                          BindingResult bindingResult,
                          HttpSession httpSession){
//        if (bindingResult.hasErrors()) {
//            return "RegisterPage";
//        }
//        userService.updateDisable();
        return "redirect:/users/all";
    }
    @PostMapping("/users/enable")
    public String enable(){

        return "redirect:/users/all";
    }
}
