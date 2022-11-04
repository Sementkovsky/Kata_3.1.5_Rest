package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import java.security.Principal;

@Controller
public class HelloController {
    private final UserDao userDao;

    public HelloController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/user")
    public String printWelcome(ModelMap model, Principal principal) {
        User user = userDao.findByName(principal.getName());
        model.addAttribute("messages", user);
        return "user";
    }
}


