package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionscategorie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionscategorieReference;

    private String transactionscategorieLibelle;
    private String transactionscategorieDescription;

    @ManyToOne
    @JoinColumn(name = "categorieNature_id")
    private TransactionCategorieNature transactionCategorieNature;

    @OneToMany(mappedBy = "transactionCategorie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;

    @OneToMany(mappedBy = "transactionCategorie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCaisse> transactionsCaisse;
}