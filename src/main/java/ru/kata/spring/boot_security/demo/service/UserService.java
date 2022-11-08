package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getById(Long id);

    List<User> listUsers();

    void saveUser(User user);

    void removeUser(Long id);

    void updateUser(User user);

    boolean add(User user);

    List<Role> listRoles();

    List<Role> listByRole(List<String> lsr);

    User findByUsername(String userName);

}