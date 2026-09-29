package com.app.compta.service;

import com.app.compta.entity.FactureClient;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.ClientRepository;
import com.app.compta.repository.DeviseRepository;
import com.app.compta.repository.FactureClientCategorieRepository;
import com.app.compta.repository.FactureClientRepository;
import com.app.compta.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureClientService {

    private final FactureClientRepository factureClientRepository;
    private final ClientRepository clientRepository;
    private final DeviseRepository deviseRepository;
    private final FactureClientCategorieRepository categorieRepository;
    private final PdfGenerator pdfGenerator;

    public List<FactureClient> getAllFacturesClient() {
        // Load the client so the Angular list can display the client name
        return factureClientRepository.findAllWithClient();
    }

    public FactureClient getFactureClientById(int id) {
        // Load client, devise and categorie so the PDF and edit form have the data
        return factureClientRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture client non trouvée avec l'id: " + id));
    }

    public byte[] getFacturePdf(int id) {
        FactureClient facture = factureClientRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new RuntimeException("Facture not found"));

        return pdfGenerator.generateFactureClientPdf(facture);
    }

    public List<FactureClient> getFacturesByClient(int clientId) {
        return factureClientRepository.findByClientClientsReference(clientId);
    }

    public FactureClient createFactureClient(FactureClient factureClient) {
        setRelations(factureClient, factureClient);
        return factureClientRepository.save(factureClient);
    }

    public FactureClient updateFactureClient(int id, FactureClient factureClient) {
        FactureClient existing = getFactureClientById(id);
        existing.setFacturesClientNumero(factureClient.getFacturesClientNumero());
        existing.setFacturesClientMontantDevise(factureClient.getFacturesClientMontantDevise());
        existing.setFacturesClientTauxChange(factureClient.getFacturesClientTauxChange());
        existing.setFacturesClientHT(factureClient.getFacturesClientHT());
        existing.setFacturesClientTVA(factureClient.getFacturesClientTVA());
        existing.setFacturesClientTTC(factureClient.getFacturesClientTTC());
        existing.setFacturesClientTimbre(factureClient.getFacturesClientTimbre());
        existing.setFacturesClientTotalFacture(factureClient.getFacturesClientTotalFacture());
        existing.setFacturesClientRSTVAPourcentage(factureClient.getFacturesClientRSTVAPourcentage());
        existing.setFacturesClientRSTVA(factureClient.getFacturesClientRSTVA());
        existing.setFacturesClientRSPourcentage(factureClient.getFacturesClientRSPourcentage());
        existing.setFacturesClientRS(factureClient.getFacturesClientRS());
        existing.setFacturesClientRSTotal(factureClient.getFacturesClientRSTotal());
        existing.setFacturesClientMontantARecevoir(factureClient.getFacturesClientMontantARecevoir());
        existing.setFacturesClientMontantVerse(factureClient.getFacturesClientMontantVerse());
        existing.setFacturesClientMontantEcart(factureClient.getFacturesClientMontantEcart());

        setRelations(existing, factureClient);
        return factureClientRepository.save(existing);
    }

    public void deleteFactureClient(int id) {
        factureClientRepository.deleteById(id);
    }

    private void setRelations(FactureClient target, FactureClient source) {
        if (source.getClient() != null && source.getClient().getClientsReference() != 0) {
            target.setClient(clientRepository.findById(source.getClient().getClientsReference())
                    .orElse(null));
        }
        if (source.getDevise() != null && source.getDevise().getDeviseReference() != 0) {
            target.setDevise(deviseRepository.findById(source.getDevise().getDeviseReference())
                    .orElse(null));
        }
        if (source.getCategorie() != null && source.getCategorie().getFactureclientcategorieReference() != 0) {
            target.setCategorie(categorieRepository.findById(source.getCategorie().getFactureclientcategorieReference())
                    .orElse(null));
        }
    }
}
