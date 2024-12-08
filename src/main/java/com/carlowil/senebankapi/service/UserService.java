package com.carlowil.senebankapi.service;

import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> Users) {
        return repository.saveAll(Users);
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

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User deleted " + id + "!";
    }

    public User updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        assert existingUser != null;
        existingUser.setEmail(user.getEmail());
        existingUser.setDate(user.getDate());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }
}
