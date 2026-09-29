package com.app.compta.service;

import com.app.compta.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClientRepository clientRepository;
    private final FournisseurRepository fournisseurRepository;
    private final FactureClientRepository factureClientRepository;
    private final FactureFrsRepository factureFrsRepository;
    private final TransactionBanqueRepository transactionBanqueRepository;
    private final TransactionCaisseRepository transactionCaisseRepository;

    public long getTotalClients() {
        return clientRepository.count();
    }

    public long getTotalFournisseurs() {
        return fournisseurRepository.count();
    }

    public long getTotalFacturesClient() {
        return factureClientRepository.count();
    }

    public long getTotalFacturesFrs() {
        return factureFrsRepository.count();
    }

    public long getTotalTransactionsBanque() {
        return transactionBanqueRepository.count();
    }

    public long getTotalTransactionsCaisse() {
        return transactionCaisseRepository.count();
    }
}