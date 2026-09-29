package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private long totalClients;
    private long totalFournisseurs;
    private long totalFacturesClient;
    private long totalFacturesFrs;
    private long totalTransactionsBanque;
    private long totalTransactionsCaisse;
}