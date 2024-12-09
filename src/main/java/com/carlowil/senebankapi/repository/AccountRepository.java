package com.carlowil.senebankapi.repository;

import com.carlowil.senebankapi.entity.Account;
import com.carlowil.senebankapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUser(User user);
}
