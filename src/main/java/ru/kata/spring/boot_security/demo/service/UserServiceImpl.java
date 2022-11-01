package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;



import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    public boolean addRole(Role role) {
        Role userBas = roleDao.findByName(role.getRole());
        if(userBas != null) {return false;}
        roleDao.add(role);
        return true;
    }

    @Transactional
    public boolean add(User user) {
        User userBas = userDao.findByName(user.getUsername());
        if(userBas != null) {return false;}
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}



