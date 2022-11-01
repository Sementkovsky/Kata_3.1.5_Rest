package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@Controller
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class HelloController {
    private final UserDaoImpl userDao;

    public HelloController(UserDaoImpl ud) {
        this.userDao = ud;
    }

    @GetMapping(value = "/user")
    public String printWelcome(ModelMap model, Principal principal) {
        User us = userDao.findByName(principal.getName());
        model.addAttribute("messages", us);
        return "user-no-admin";
    }
}


