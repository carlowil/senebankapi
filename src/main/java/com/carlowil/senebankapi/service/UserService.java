package com.carlowil.senebankapi.service;

import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.carlowil.senebankapi.entity.Role;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public User saveUser(User user) {
        return repository.save(user);
    }

    public User createUser(User user) {
        if(repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return saveUser(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User deleted " + id + "!";
    }

    public User updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        assert existingUser != null;
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setDate(user.getDate());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        repository.save(user);
    }
}
