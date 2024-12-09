package com.carlowil.senebankapi.service;

import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
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

    public List<Transaction> getTransactionsByUserId(Integer user_id) {
        return repository.findByUserId(user_id);
    }

    public List<Transaction> getTransactionsByAccountFromId(Integer account_from_id) {
        return repository.findByAccountFromId(account_from_id);
    }

    public List<Transaction> getTransactionsByAccountToId(Integer account_to_id) {
        return repository.findByAccountToId(account_to_id);
    }

    public Transaction updateTransaction(Transaction transaction) {
        Transaction existingTransaction = repository.findById(transaction.getId()).orElse(null);
        assert existingTransaction != null;
        existingTransaction.setPayload(transaction.getPayload());
        existingTransaction.setUser(transaction.getUser());
        existingTransaction.setAccount_from(transaction.getAccount_from());
        existingTransaction.setAccount_to(transaction.getAccount_to());
        return repository.save(existingTransaction);
    }
}
