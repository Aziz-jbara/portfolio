package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transactionscaisse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionscaisseReference;

    @Temporal(TemporalType.DATE)
    private Date transactionscaisseDate;

    private String transactionscaisseLibelle;

    private String transactionscaisseNumPieceComptable;

    private double transactionscaisseMontant;

    private String transactionscaisseCommentaire;

    private double transactionscaisseSoldeProgressif;

    private int transactionscaisseCompteCourantAssocie;

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
}