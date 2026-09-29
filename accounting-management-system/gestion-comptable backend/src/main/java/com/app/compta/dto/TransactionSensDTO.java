package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSensDTO {

    private int transactionsnatureReference;
    private String transactionsnatureLibelle;
    private String transactionsnatureDescription;
}