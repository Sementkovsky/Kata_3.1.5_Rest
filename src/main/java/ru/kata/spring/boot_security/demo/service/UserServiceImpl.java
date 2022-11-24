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
import java.util.stream.Collectors;

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
    public User getById(Long id) {
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
        if (userBas != null) {
            return false;
        }
        roleDao.add(role);
        return true;
    }

    @Transactional
    public boolean add(User user) {
        User userBas = userDao.findByEmail(user.getUsername());
        if (userBas != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(user);
        List<String> list = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        List<Role> roleList = listByRole(list);
        user.setRoles(roleList);
        userDao.add(user);
        return true;
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User userBase = getById(user.getId());
        if (!userBase.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        List<String> list = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        List<Role> roleList = listByRole(list);
        user.setRoles(roleList);
        userDao.updateUser(user);
    }

    public User findByEmail(String userName) {
        return userDao.findByEmail(userName);
    }

    public List<Role> listByRole(List<String> name) {
        return roleDao.listByName(name);
    }

    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }
        return user;
    }
}



