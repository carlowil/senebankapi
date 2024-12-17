package com.carlowil.senebankapi.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountFromId")
    private Account accountFrom;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    private int payload;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountToId")
    private Account accountTo;
}
