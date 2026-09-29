package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionssens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionsnatureReference;

    private String transactionsnatureLibelle;
    private String transactionsnatureDescription;

    @OneToMany(mappedBy = "transactionSens", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;

    @OneToMany(mappedBy = "transactionSens", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCaisse> transactionsCaisse;
}