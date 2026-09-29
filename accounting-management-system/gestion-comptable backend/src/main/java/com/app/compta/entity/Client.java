package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientsReference;

    private String clientsRaisonSocial;
    private String clientsAdresse;
    private String clientsCodePostal;
    private String clientsTelephone;
    private String clientsCodeTVA;
    private String clientsFax;
    private String clientsGouvernorat;
    private String clientsPays;
    @JsonIgnore
    @ToString.Exclude   // ← ajouter cette ligne
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<FactureClient> facturesClient;
}