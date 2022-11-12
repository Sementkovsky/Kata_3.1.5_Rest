package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public ModelAndView admin(Principal user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/Admin_cop");

        List<User> users = userService.listUsers();
        modelAndView.addObject("users", users);

        modelAndView.addObject("admin", userService.findByEmail(user.getName()));
        return modelAndView;
    }

    //   @GetMapping(value = "/all")
    //   public String showUsers(Model model) {
    //       model.addAttribute("listUsers", userService.listUsers());
    //       return "admin/Admin_cop";
    //   }

    @GetMapping("/add")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roleList", userService.listRoles());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.add(user);
        return "redirect:/admin";
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

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}


