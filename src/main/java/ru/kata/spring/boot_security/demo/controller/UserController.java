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

    @GetMapping(value = "/admin")
    public String showUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roleList", userService.listRoles());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.add(user);
        return "result";
    }

    @GetMapping("user-update/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.listRoles());
        return "/user-update";
    }

    @PatchMapping("/user-update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/user-delete";
    }

    @DeleteMapping("/user-delete")
    public String deleteUser(Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}