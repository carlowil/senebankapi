package com.carlowil.senebankapi.repository;

import com.carlowil.senebankapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(Integer user_id);
}
