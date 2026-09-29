    package com.app.compta.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.*;
    import java.util.List;

    @Entity
    @Table(name = "facturesclient")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class FactureClient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        @ManyToOne
        @JoinColumn(name = "client_id")
        private Client client;

        @ManyToOne
        @JoinColumn(name = "devise_id")
        private Devise devise;

        @ManyToOne
        @JoinColumn(name = "categorie_id")
        private FactureClientCategorie categorie;
        @JsonIgnore
        @OneToMany(mappedBy = "factureClient", cascade = CascadeType.ALL)
        private List<TransactionBanque> transactionsBanque;
        @JsonIgnore
        @OneToMany(mappedBy = "factureClient", cascade = CascadeType.ALL)
        private List<TransactionCaisse> transactionsCaisse;
    }