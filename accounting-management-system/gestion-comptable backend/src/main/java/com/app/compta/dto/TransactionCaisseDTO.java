package com.app.compta.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCaisseDTO {

    private int transactionscaisseReference;
    private Date transactionscaisseDate;
    private String transactionscaisseLibelle;
    private String transactionscaisseNumPieceComptable;
    private double transactionscaisseMontant;
    private String transactionscaisseCommentaire;
    private double transactionscaisseSoldeProgressif;
    private int transactionscaisseCompteCourantAssocie;

    // References only to avoid circular dependencies
    private int factureClientId;
    private int factureFrsId;
    private int etatId;
    private int categorieId;
    private int sensId;
}