package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.dto.JwtAuthenticationResponse;
import com.carlowil.senebankapi.dto.SignInRequest;
import com.carlowil.senebankapi.dto.SignUpRequest;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.AuthenticationService;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationService authServ;

    @GetMapping("/auth/authorizeUser")
    public JwtAuthenticationResponse authorizeUser(@RequestBody SignInRequest request) {
        return authServ.signIn(request);
    }
    @GetMapping("/auth/registerUser")
    public JwtAuthenticationResponse registerUser(@RequestBody SignUpRequest request) {
        return authServ.signUp(request);
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
