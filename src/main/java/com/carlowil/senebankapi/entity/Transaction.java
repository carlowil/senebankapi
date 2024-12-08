package com.carlowil.senebankapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany
    private int account_from_id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private int user_id;
    private int payload;
    @OneToMany
    private int account_to_id;
}
