package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "factureclientcategorie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureClientCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factureclientcategorieReference;

    private String factureclientcategorieLibelle;
    private String factureclientcategorieDescription;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude   // ← ajouter cette ligne
    private List<FactureClient> facturesClient;
}