package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionscategorienature")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategorieNature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionscategorienatureReference;

    private String transactionscategorienatureLibelle;

    @OneToMany(mappedBy = "transactionCategorieNature", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCategorie> transactionsCategorie;
}