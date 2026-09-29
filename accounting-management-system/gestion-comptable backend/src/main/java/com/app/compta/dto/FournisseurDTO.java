package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDTO {

    private int fournisseurReference;
    private String fournisseurRaisonSocial;
    private String fournisseurAdresse;
    private String fournisseurCodePostal;
    private String fournisseurTelephone;
    private String fournisseurCodeTVA;
    private String fournisseursGouvernorat;
    private String fournisseursPays;
    private String fournisseursFax;
}