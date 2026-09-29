package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsReleveDTO {

    private int transactionsreleveReference;
    private int transactionsreleveAnnee;
    private String transactionsreleveMois;
    private int transactionsreleveEtat;
    private double transactionsreleveMontant;
}