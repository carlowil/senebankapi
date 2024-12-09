package com.carlowil.senebankapi.repository;

import com.carlowil.senebankapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Integer user_id);
    List<Transaction> findByAccountFromId(Integer account_from_id);
    List<Transaction> findByAccountToId(Integer account_to_id);
}
