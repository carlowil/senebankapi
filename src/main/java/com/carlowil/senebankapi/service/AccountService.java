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

    public String saveAccount(Account account) {
        repository.save(account);
        return String.format("Account id: %d created !", account.getId());
    }

    public String saveAccounts(List<Account> accounts) {
        repository.saveAll(accounts);
        return "Accounts id: " + accounts.stream().map(Account::getId).toString() + "created !";
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

    public String updateAccount(Account account) {
        Account existingAccount = repository.findById(account.getId()).orElse(null);
        assert existingAccount != null;
        existingAccount.setBalance(account.getBalance());
        existingAccount.setOverdraft(account.isOverdraft());
        existingAccount.setUser(account.getUser());
        repository.save(existingAccount);
        return String.format("Account id: %d updated!", account.getId());
    }
}

