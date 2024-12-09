package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;
    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.saveTransaction(transaction);
    }

    @PostMapping("/addTransactions")
    public List<Transaction> addTransactions(@RequestBody List<Transaction> transactions) {
        return service.saveTransactions(transactions);
    }

    @GetMapping("/transactions")
    public List<Transaction> findAllTransactions() {
        return service.getTransactions();
    }

    @GetMapping("/transactionById/{id}")
    public Transaction findTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @GetMapping("/transactionsByAccountFromId/{account_from_id}")
    public List<Transaction> findTransactionsByAccountFromId(@PathVariable Integer account_from_id) {
        return service.getTransactionsByAccountFromId(account_from_id);
    }

    @GetMapping("/transactionsByAccountToId/{account_to_id}")
    public List<Transaction> findTransactionsByAccountToId(@PathVariable Integer account_to_id) {
        return service.getTransactionsByAccountToId(account_to_id);
    }

    @GetMapping("/transactionsByUserId/{user_id}")
    public List<Transaction> findTransactionsByUserId(@PathVariable Integer user_id) {
        return service.getTransactionsByUserId(user_id);
    }

    @PutMapping("/update")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return service.updateTransaction(transaction);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }
}
