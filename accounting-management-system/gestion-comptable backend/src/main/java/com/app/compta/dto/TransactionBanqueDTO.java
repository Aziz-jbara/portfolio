package com.app.compta.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBanqueDTO {

    private int transactionsbanqueReference;
    private Date transactionsbanqueDateReelle;
    private Date transactionsbanqueDateOperation;
    private String transactionsbanqueLibelle;
    private double transactionsbanqueMontant;
    private String transactionsbanqueCommentaire;
    private String transactionsbanqueNumeroDeType;

    // References only to avoid circular dependencies
    private int releveId;
    private int factureClientId;
    private int factureFrsId;
    private int etatId;
    private int categorieId;
    private int sensId;
    private int typeId;
}