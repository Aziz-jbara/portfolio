package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionsetat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEtat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionsetatReference;

    private String transactionsetatLibelle;
    private String transactionsetatDescription;
    private int transactionsetatOrdre;

    @OneToMany(mappedBy = "transactionEtat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;

    @OneToMany(mappedBy = "transactionEtat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCaisse> transactionsCaisse;
}