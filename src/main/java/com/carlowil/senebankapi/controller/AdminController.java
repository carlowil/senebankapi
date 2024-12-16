package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.AccountService;
import com.carlowil.senebankapi.service.TransactionService;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private TransactionService serviceTransaction;
    @Autowired
    private AccountService serviceAccount;
    @Autowired
    private UserService serviceUser;
    @GetMapping("/admin/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> findAllTransactions() {
        return serviceTransaction.getTransactions();
    }

    @GetMapping("/admin/transactionById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transaction findTransactionById(@PathVariable int id) {
        return serviceTransaction.getTransactionById(id);
    }

    @GetMapping("/admin/transactionsByAccountFromId/{account_from_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> findTransactionsByAccountFromId(@PathVariable Integer account_from_id) {
        Account account = serviceAccount.getAccountById(account_from_id);
        return serviceTransaction.getTransactionsByAccountFrom(account);
    }

    @GetMapping("/admin/transactionsByAccountToId/{account_to_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> findTransactionsByAccountToId(@PathVariable Integer account_to_id) {
        Account account = serviceAccount.getAccountById(account_to_id);
        return serviceTransaction.getTransactionsByAccountTo(account);
    }

    @GetMapping("/admin/transactionsByUserId/{user_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> findTransactionsByUserId(@PathVariable Integer user_id) {
        User user = serviceUser.getUserById(user_id);
        return serviceTransaction.getTransactionsByUser(user);
    }

    @GetMapping("/admin/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> findAllAccounts() {
        return serviceAccount.getAccounts();
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAllUsers() {
        return serviceUser.getUsers();
    }

    @PutMapping("/admin/updateAccount")
    @PreAuthorize("hasRole('ADMIN')")
    public Account updateAccount(@RequestBody Account account) {
        return serviceAccount.updateAccount(account);
    }

    @GetMapping("/admin/userById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User findUserById(@PathVariable int id) {
        return serviceUser.getUserById(id);
    }

    @PostMapping("/admin/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        return serviceUser.createUser(user);
    }

    @GetMapping("/admin/userByEmail/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public User findUserByEmail(@PathVariable String email) {
        return serviceUser.getUserByEmail(email);
    }
}
