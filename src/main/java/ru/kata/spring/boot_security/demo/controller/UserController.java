package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String showUserPage() {
        return "user/user";
    }

    @GetMapping("/api/user")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<User> showUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}