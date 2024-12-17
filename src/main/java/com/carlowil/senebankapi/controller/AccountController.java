package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.dto.CreateAccountRequest;
import com.carlowil.senebankapi.dto.CreateAccountResponse;
import com.carlowil.senebankapi.dto.TransactionResponse;
import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
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
    public CreateAccountResponse addAccount(@RequestBody CreateAccountRequest account) {
        User thisUser = serviceUser.getUserById(account.getUserId());
        Account thisAccount = Account.builder()
                .isOverdraft(account.getIsOverdraft())
                .balance(account.getBalance())
                .user(thisUser)
                .build();
        return CreateAccountResponse.builder().message(serviceAccount.saveAccount(thisAccount)).build();
    }

    @PostMapping("/addAccounts")
    public CreateAccountResponse addAccounts(@RequestBody List<CreateAccountRequest> accounts) {
        List<Account> listAccounts = new java.util.ArrayList<>(List.of());
        for(CreateAccountRequest account : accounts) {
            User thisUser = serviceUser.getUserById(account.getUserId());
            Account thisAccount = Account.builder()
                    .isOverdraft(account.getIsOverdraft())
                    .balance(account.getBalance())
                    .user(thisUser)
                    .build();
            listAccounts.add(thisAccount);
        }
        return CreateAccountResponse.builder().message(serviceAccount.saveAccounts(listAccounts)).build();
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
