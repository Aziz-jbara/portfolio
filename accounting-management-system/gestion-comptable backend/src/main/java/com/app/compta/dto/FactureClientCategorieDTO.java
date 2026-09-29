package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureClientCategorieDTO {

    private int factureclientcategorieReference;
    private String factureclientcategorieLibelle;
    private String factureclientcategorieDescription;
}