package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private int clientsReference;
    private String clientsRaisonSocial;
    private String clientsAdresse;
    private String clientsCodePostal;
    private String clientsTelephone;
    private String clientsCodeTVA;
    private String clientsFax;
    private String clientsGouvernorat;
    private String clientsPays;
}