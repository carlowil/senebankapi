package com.carlowil.senebankapi.controller;

import com.carlowil.senebankapi.dto.TransactionRequest;
import com.carlowil.senebankapi.dto.TransactionResponse;
import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.service.AccountService;
import com.carlowil.senebankapi.service.TransactionService;
import com.carlowil.senebankapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService serviceTransaction;
    @Autowired
    private AccountService serviceAccount;
    @Autowired
    private UserService serviceUser;
    @PostMapping("/addTransaction")
    public TransactionResponse addTransaction(@RequestBody TransactionRequest transaction) {
        Account accountFrom = serviceAccount.getAccountById(transaction.getAccountFromId());
        Account accountTo = serviceAccount.getAccountById(transaction.getAccountToId());
        if (!(accountFrom.getBalance() >= transaction.getPayload())) {
            return TransactionResponse.builder().message(String.format("Not enough money on account: %d", transaction.getAccountFromId())).build();
        }
        User thisUser = serviceUser.getUserById(transaction.getUserId());
        accountFrom.setBalance(accountFrom.getBalance() - transaction.getPayload());
        accountTo.setBalance(accountTo.getBalance() + transaction.getPayload());
        serviceAccount.updateAccount(accountFrom);
        serviceAccount.updateAccount(accountTo);
        Transaction realTransaction = Transaction.builder()
                .user(thisUser)
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .payload(transaction.getPayload())
                .build();
        return TransactionResponse.builder().message(serviceTransaction.saveTransaction(realTransaction)).build();
    }

    @PostMapping("/addTransactions")
    public List<TransactionResponse> addTransactions(@RequestBody List<TransactionRequest> transactions) {
        List<TransactionResponse> listResponse = new java.util.ArrayList<>(List.of());
        for (TransactionRequest transaction : transactions) {
            Account accountFrom = serviceAccount.getAccountById(transaction.getAccountFromId());
            Account accountTo = serviceAccount.getAccountById(transaction.getAccountToId());
            if (!(accountFrom.getBalance() >= transaction.getPayload())) {
                listResponse.add(TransactionResponse.builder().message(String.format("Not enough money on account: %d", transaction.getAccountFromId())).build());
            }
            User thisUser = serviceUser.getUserById(transaction.getUserId());
            accountFrom.setBalance(accountFrom.getBalance() - transaction.getPayload());
            accountTo.setBalance(accountTo.getBalance() + transaction.getPayload());
            serviceAccount.updateAccount(accountFrom);
            serviceAccount.updateAccount(accountTo);
            Transaction realTransaction = Transaction.builder()
                    .user(thisUser)
                    .accountFrom(accountFrom)
                    .accountTo(accountTo)
                    .payload(transaction.getPayload())
                    .build();
            listResponse.add(TransactionResponse.builder().message(serviceTransaction.saveTransaction(realTransaction)).build());
        }
        return listResponse;
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
