package com.carlowil.senebankapi.repository;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.Transaction;
import com.carlowil.senebankapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByAccountFrom(Account account);
    List<Transaction> findByAccountTo(Account account);
}
