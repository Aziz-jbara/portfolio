package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEtatDTO {

    private int transactionsetatReference;
    private String transactionsetatLibelle;
    private String transactionsetatDescription;
    private int transactionsetatOrdre;
}