package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
}