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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    private int balance;
    @JoinColumn(name = "isOverdraft")
    private boolean isOverdraft;
}
