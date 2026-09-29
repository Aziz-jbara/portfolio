package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureClientDTO {

    private int facturesClientReference;
    private int facturesClientNumero;
    private double facturesClientMontantDevise;
    private float facturesClientTauxChange;
    private double facturesClientHT;
    private float facturesClientTVA;
    private double facturesClientTTC;
    private float facturesClientTimbre;
    private double facturesClientTotalFacture;
    private float facturesClientRSTVAPourcentage;
    private float facturesClientRSTVA;
    private float facturesClientRSPourcentage;
    private float facturesClientRS;
    private double facturesClientRSTotal;
    private double facturesClientMontantARecevoir;
    private double facturesClientMontantVerse;
    private double facturesClientMontantEcart;

    // References only to avoid circular dependencies
    private int clientId;
    private int deviseId;
    private int categorieId;
}