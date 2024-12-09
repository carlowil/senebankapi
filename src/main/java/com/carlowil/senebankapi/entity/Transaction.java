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
    private int account_from_id;
    @ManyToOne(cascade = CascadeType.ALL)
    private int user_id;
    private int payload;
    @ManyToOne
    private int account_to_id;
}
