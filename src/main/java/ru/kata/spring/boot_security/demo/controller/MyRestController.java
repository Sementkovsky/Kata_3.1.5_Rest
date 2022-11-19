package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {


    private final UserService userService;


    public MyRestController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("")
//    public List<User> getAll() {
// List<User> a = userService.listUsers();
//        return a;
    //  }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }


}