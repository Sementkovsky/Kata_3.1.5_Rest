package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;



import java.util.List;

public interface UserDao {

    User getById(Long id);

    List<User> listUsers();
    public boolean add(User user);
    void saveUser(User user);

    void removeUser(Long id);

    void updateUser(User user);

    User findByName(String username);

}
