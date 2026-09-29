package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transactionsbanque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionsbanqueReference;

    @Temporal(TemporalType.DATE)
    private Date transactionsbanqueDateReelle;

    @Temporal(TemporalType.DATE)
    private Date transactionsbanqueDateOperation;

    private String transactionsbanqueLibelle;

    private double transactionsbanqueMontant;

    private String transactionsbanqueCommentaire;

    private String transactionsbanqueNumeroDeType;

    @ManyToOne
    @JoinColumn(name = "releve_id")
    @ToString.Exclude
    private TransactionsReleve transactionsReleve;

    @ManyToOne
    @JoinColumn(name = "factureClient_id")
    @ToString.Exclude
    private FactureClient factureClient;

    @ManyToOne
    @JoinColumn(name = "factureFrs_id")
    @ToString.Exclude
    private FactureFrs factureFrs;

    @ManyToOne
    @JoinColumn(name = "etat_id")
    @ToString.Exclude
    private TransactionEtat transactionEtat;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @ToString.Exclude
    private TransactionCategorie transactionCategorie;

    @ManyToOne
    @JoinColumn(name = "sens_id")
    @ToString.Exclude
    private TransactionSens transactionSens;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @ToString.Exclude
    private TransactionBanqueType transactionBanqueType;
}