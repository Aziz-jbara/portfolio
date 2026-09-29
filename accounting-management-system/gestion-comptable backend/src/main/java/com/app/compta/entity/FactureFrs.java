package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "facturefrs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureFrs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facturesFRSReference;

    private String facturesFRSNumero;
    private double facturesFRSMontantDevise;
    private float facturesFRSTauxChange;
    private double facturesFRSHT;
    private float facturesFRSTVAPourcentage;
    private float facturesFRSTVA;
    private double facturesFRSTTC;
    private float facturesFRSTimbre;
    private double facturesFRSTotalFacture;
    private float facturesFRSRSPourcentage;
    private double facturesFRSRSTotal;
    private double facturesFRSMontantAPayer;
    private double facturesFRSMontantPayee;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;

    @ManyToOne
    @JoinColumn(name = "rsAttestation_id")
    private RsAttestationRecuperee rsAttestationRecuperee;

    @OneToMany(mappedBy = "factureFrs", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBanque> transactionsBanque;

    @OneToMany(mappedBy = "factureFrs", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCaisse> transactionsCaisse;
}