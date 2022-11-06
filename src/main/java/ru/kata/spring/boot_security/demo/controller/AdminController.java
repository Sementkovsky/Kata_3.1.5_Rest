package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String startPage() {
        return "admin/home";
    }

    @GetMapping(value = "/all")
    public String showUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "admin/all";
    }

    @GetMapping("/add")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roleList", userService.listRoles());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.add(user);
        return "admin/result";
    }

    @GetMapping("user-update/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.listRoles());
        return "admin/user-update";
    }

    @PatchMapping("/user-update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "admin/user-delete";
    }

    @DeleteMapping("/user-delete")
    public String deleteUser(Long id) {
        userService.removeUser(id);
        return "redirect:/admin/all";
    }
}