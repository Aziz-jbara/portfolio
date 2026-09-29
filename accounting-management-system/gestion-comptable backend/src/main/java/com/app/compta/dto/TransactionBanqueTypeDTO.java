package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBanqueTypeDTO {

    private int transactionbanquetypesReference;
    private String transactionbanquetypesLibelle;
    private String transactionbanquetypesDescription;
}