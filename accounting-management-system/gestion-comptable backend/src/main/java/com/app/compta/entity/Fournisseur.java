package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fournisseur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fournisseurReference;

    private String fournisseurRaisonSocial;
    private String fournisseurAdresse;
    private String fournisseurCodePostal;
    private String fournisseurTelephone;
    private String fournisseurCodeTVA;
    private String fournisseursGouvernorat;
    private String fournisseursPays;
    private String fournisseursFax;
    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<FactureFrs> facturesFrs;
}