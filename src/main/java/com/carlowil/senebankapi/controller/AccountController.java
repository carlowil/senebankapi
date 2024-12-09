package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.AccountService;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService serviceAccount;
    @Autowired
    private UserService serviceUser;
    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody Account account) {
        return serviceAccount.saveAccount(account);
    }

    @PostMapping("/addAccounts")
    public List<Account> addAccounts(@RequestBody List<Account> accounts) {
        return serviceAccount.saveAccounts(accounts);
    }

    @GetMapping("/accountById/{id}")
    public Account findAccountById(@PathVariable int id) {
        return serviceAccount.getAccountById(id);
    }

    @GetMapping("/accountsByUserId/{user_id}")
    public List<Account> findAccountsByUserId(@PathVariable Integer user_id) {
        User user = serviceUser.getUserById(user_id);
        return serviceAccount.getAccountsByUser(user);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable int id) {
        return serviceAccount.deleteAccount(id);
    }
}
