package com.carlowil.senebankapi.service;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public String saveTransaction(Transaction transaction) {
        repository.save(transaction);
        return String.format("Transaction finished! Transaction id - %d", transaction.getId());
    }

    public List<Transaction> saveTransactions(List<Transaction> transactions) {
        return repository.saveAll(transactions);
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteTransaction(int id) {
        repository.deleteById(id);
        return "Transaction deleted " + id + "!";
    }

    public List<Transaction> getTransactionsByUser(User user) {
        return repository.findByUser(user);
    }

    public List<Transaction> getTransactionsByAccountFrom(Account account) {
        return repository.findByAccountFrom(account);
    }

    public List<Transaction> getTransactionsByAccountTo(Account account) {
        return repository.findByAccountTo(account);
    }

    public Transaction updateTransaction(Transaction transaction) {
        Transaction existingTransaction = repository.findById(transaction.getId()).orElse(null);
        assert existingTransaction != null;
        existingTransaction.setPayload(transaction.getPayload());
        existingTransaction.setUser(transaction.getUser());
        existingTransaction.setAccountFrom(transaction.getAccountFrom());
        existingTransaction.setAccountTo(transaction.getAccountTo());
        return repository.save(existingTransaction);
    }
}
