package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.service.AccountService;
import com.carlowil.senebankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService serviceTransaction;
    @Autowired
    private AccountService serviceAccount;
    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        Account accountFrom = transaction.getAccount_from();
        Account accountTo = transaction.getAccount_to();
        accountFrom.setBalance(accountFrom.getBalance() - transaction.getPayload());
        accountTo.setBalance(accountTo.getBalance() + transaction.getPayload());
        serviceAccount.updateAccount(accountFrom);
        serviceAccount.updateAccount(accountTo);
        return serviceTransaction.saveTransaction(transaction);
    }

    @PostMapping("/addTransactions")
    public List<Transaction> addTransactions(@RequestBody List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            Account accountFrom = transaction.getAccount_from();
            Account accountTo = transaction.getAccount_to();
            int payload = transaction.getPayload();
            accountFrom.setBalance(accountFrom.getBalance() - payload);
            accountTo.setBalance(accountTo.getBalance() + payload);
            serviceAccount.updateAccount(accountFrom);
            serviceAccount.updateAccount(accountTo);
        }
        return serviceTransaction.saveTransactions(transactions);
    }

//    @PutMapping("/updateTransaction")
//    public Transaction updateTransaction(@RequestBody Transaction transaction) {
//        return serviceTransaction.updateTransaction(transaction);
//    }
//
//    @DeleteMapping("/deleteTransaction/{id}")
//    public String deleteTransaction(@PathVariable int id) {
//        return serviceTransaction.deleteTransaction(id);
//    }
}
