package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String startPage() {
        return "index";
    }

    @GetMapping(value = "/users")
    public String showUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userService.saveUser(user);
        return "result";
    }

    @GetMapping("/user-update/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PatchMapping("/user-update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteForm(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/user-delete";
    }

    @DeleteMapping("/user-delete")
    public String deleteUser(int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }
}



