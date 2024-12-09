package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;
    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody Account account) {
        return service.saveAccount(account);
    }

    @PostMapping("/addAccounts")
    public List<Account> addAccounts(@RequestBody List<Account> accounts) {
        return service.saveAccounts(accounts);
    }

    @GetMapping("/accountById/{id}")
    public Account findAccountById(@PathVariable int id) {
        return service.getAccountById(id);
    }

    @GetMapping("/accountsByUserId/{user_id}")
    public List<Account> findAccountsByUserId(@PathVariable Integer user_id) {
        return service.getAccountsByUserId(user_id);
    }

//    @PutMapping("/updateAccount")
//    public Account updateAccount(@RequestBody Account account) {
//        return service.updateAccount(account);
//    }

    @DeleteMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable int id) {
        return service.deleteAccount(id);
    }
}
