package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureFrsDTO {

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

    // References only to avoid circular dependencies
    private int fournisseurId;
    private int deviseId;
    private int rsAttestationId;
}