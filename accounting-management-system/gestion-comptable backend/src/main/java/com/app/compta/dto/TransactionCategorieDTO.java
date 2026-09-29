package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategorieDTO {

    private int transactionscategorieReference;
    private String transactionscategorieLibelle;
    private String transactionscategorieDescription;

    // Reference only to avoid circular dependencies
    private int categorieNatureId;
}