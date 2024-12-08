package com.carlowil.senebankapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private int user_id;
    private int balance;
    private boolean is_overdraft;
}
