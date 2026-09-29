package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int utilisateurReference;

    private String utilisateurNom;
    private String utilisateurPrenom;
    private String utilisateurEmail;
    private String utilisateurMotDePasse;
    private String utilisateurTelephone;

    @Enumerated(EnumType.STRING)
    private Role utilisateurRole;
}