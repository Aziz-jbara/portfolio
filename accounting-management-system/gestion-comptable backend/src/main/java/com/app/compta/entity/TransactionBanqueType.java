package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "transactionbanquetypes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBanqueType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionbanquetypesReference;

    private String transactionbanquetypesLibelle;
    private String transactionbanquetypesDescription;

    @OneToMany(mappedBy = "transactionBanqueType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;
}