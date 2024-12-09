package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/authorizeUser")
    public String authorizeUser(@RequestBody User user) {
        return "TODO";
    }

    @GetMapping("/unauthorizeUser")
    public String unauthorizeUser(@RequestBody User user) {
        return "TODO";
    }

//    @PutMapping("/updateUser")
//    public User updateUser(@RequestBody User User) {
//        return service.updateUser(User);
//    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

}
