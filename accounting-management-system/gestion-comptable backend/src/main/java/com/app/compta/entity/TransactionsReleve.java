package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionsreleve")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsReleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionsreleveReference;

    private int transactionsreleveAnnee;
    private String transactionsreleveMois;
    private int transactionsreleveEtat;
    private double transactionsreleveMontant;

    @OneToMany(mappedBy = "transactionsReleve", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;
}