package com.carlowil.senebankapi.service;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.User;
import com.carlowil.senebankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    public List<Account> saveAccounts(List<Account> accounts) {
        return repository.saveAll(accounts);
    }

    public List<Account> getAccounts() {
        return repository.findAll();
    }

    public Account getAccountById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteAccount(int id) {
        repository.deleteById(id);
        return "Account deleted " + id + "!";
    }

    public List<Account> getAccountsByUser(User user) {
        return repository.findByUser(user);
    }

    public Account updateAccount(Account account) {
        Account existingAccount = repository.findById(account.getId()).orElse(null);
        assert existingAccount != null;
        existingAccount.setBalance(account.getBalance());
        existingAccount.setOverdraft(account.isOverdraft());
        existingAccount.setUser(account.getUser());
        return repository.save(existingAccount);
    }
}

