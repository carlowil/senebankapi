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
    @ManyToOne
    @JoinColumn(name = "account_from_id")
    private Account account_from;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private int payload;
    @ManyToOne
    @JoinColumn(name = "account_to_id")
    private Account account_to;
}
