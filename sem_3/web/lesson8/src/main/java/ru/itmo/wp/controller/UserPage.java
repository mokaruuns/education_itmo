package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String loadPageUser(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.parseLong(id);
            User user = userService.findById(longId);
            if (user == null) {
                model.addAttribute("message", "No such user");
            } else {
                model.addAttribute("user", user);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "No such user");
        }

        return "UserPage";
    }
}
