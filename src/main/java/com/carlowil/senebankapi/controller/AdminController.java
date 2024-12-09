package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.AccountService;
import com.carlowil.senebankapi.service.TransactionService;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private TransactionService serviceTransaction;
    @Autowired
    private AccountService serviceAccount;
    @Autowired
    private UserService serviceUser;
    @GetMapping("/transactions")
    public List<Transaction> findAllTransactions() {
        return serviceTransaction.getTransactions();
    }

    @GetMapping("/transactionById/{id}")
    public Transaction findTransactionById(@PathVariable int id) {
        return serviceTransaction.getTransactionById(id);
    }

    @GetMapping("/transactionsByAccountFromId/{account_from_id}")
    public List<Transaction> findTransactionsByAccountFromId(@PathVariable Integer account_from_id) {
        return serviceTransaction.getTransactionsByAccountFromId(account_from_id);
    }

    @GetMapping("/transactionsByAccountToId/{account_to_id}")
    public List<Transaction> findTransactionsByAccountToId(@PathVariable Integer account_to_id) {
        return serviceTransaction.getTransactionsByAccountToId(account_to_id);
    }

    @GetMapping("/transactionsByUserId/{user_id}")
    public List<Transaction> findTransactionsByUserId(@PathVariable Integer user_id) {
        return serviceTransaction.getTransactionsByUserId(user_id);
    }

    @GetMapping("/accounts")
    public List<Account> findAllAccounts() {
        return serviceAccount.getAccounts();
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return serviceUser.getUsers();
    }

    @GetMapping("/userById/{id}")
    public User findUserById(@PathVariable int id) {
        return serviceUser.getUserById(id);
    }

    @GetMapping("/userByEmail/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return serviceUser.getUserByEmail(email);
    }
}
