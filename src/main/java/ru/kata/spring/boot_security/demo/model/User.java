package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public String getEmail() {
        return email;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "age")
    int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
    }

    public User(String name, String secondName, int age) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
    }

    public User(String email, String password, String name, String secondName, int age, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.roles = roles;
    }

    public User(Long id, String email, String password, String name, String secondName, int age, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
