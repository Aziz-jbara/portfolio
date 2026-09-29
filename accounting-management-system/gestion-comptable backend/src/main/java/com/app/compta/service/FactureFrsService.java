package com.app.compta.service;

import com.app.compta.entity.Devise;
import com.app.compta.entity.FactureFrs;
import com.app.compta.entity.Fournisseur;
import com.app.compta.entity.RsAttestationRecuperee;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.DeviseRepository;
import com.app.compta.repository.FactureFrsRepository;
import com.app.compta.repository.FournisseurRepository;
import com.app.compta.repository.RsAttestationRecupereeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureFrsService {

    private final FactureFrsRepository factureFrsRepository;
    private final FournisseurRepository fournisseurRepository;
    private final DeviseRepository deviseRepository;
    private final RsAttestationRecupereeRepository rsAttestationRecupereeRepository;

    public List<FactureFrs> getAllFacturesFrs() {
        return factureFrsRepository.findAll();
    }

    public FactureFrs getFactureFrsById(int id) {
        return factureFrsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture fournisseur non trouvée avec l'id: " + id));
    }

    public List<FactureFrs> getFacturesByFournisseur(int fournisseurId) {
        return factureFrsRepository.findByFournisseurFournisseurReference(fournisseurId);
    }

    public FactureFrs createFactureFrs(FactureFrs factureFrs) {
        attachRelations(factureFrs, factureFrs);
        return factureFrsRepository.save(factureFrs);
    }

    public FactureFrs updateFactureFrs(int id, FactureFrs factureFrs) {
        FactureFrs existing = getFactureFrsById(id);

        existing.setFacturesFRSNumero(factureFrs.getFacturesFRSNumero());
        existing.setFacturesFRSMontantDevise(factureFrs.getFacturesFRSMontantDevise());
        existing.setFacturesFRSTauxChange(factureFrs.getFacturesFRSTauxChange());
        existing.setFacturesFRSHT(factureFrs.getFacturesFRSHT());
        existing.setFacturesFRSTVAPourcentage(factureFrs.getFacturesFRSTVAPourcentage());
        existing.setFacturesFRSTVA(factureFrs.getFacturesFRSTVA());
        existing.setFacturesFRSTTC(factureFrs.getFacturesFRSTTC());
        existing.setFacturesFRSTimbre(factureFrs.getFacturesFRSTimbre());
        existing.setFacturesFRSTotalFacture(factureFrs.getFacturesFRSTotalFacture());
        existing.setFacturesFRSRSPourcentage(factureFrs.getFacturesFRSRSPourcentage());
        existing.setFacturesFRSRSTotal(factureFrs.getFacturesFRSRSTotal());
        existing.setFacturesFRSMontantAPayer(factureFrs.getFacturesFRSMontantAPayer());
        existing.setFacturesFRSMontantPayee(factureFrs.getFacturesFRSMontantPayee());

        attachRelations(existing, factureFrs);

        return factureFrsRepository.save(existing);
    }

    public void deleteFactureFrs(int id) {
        factureFrsRepository.deleteById(id);
    }

    private void attachRelations(FactureFrs target, FactureFrs source) {

        if (source.getFournisseur() != null) {
            int fournisseurId = source.getFournisseur().getFournisseurReference();

            Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                    .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé avec l'id: " + fournisseurId));

            target.setFournisseur(fournisseur);
        } else {
            target.setFournisseur(null);
        }

        if (source.getDevise() != null) {
            int deviseId = source.getDevise().getDeviseReference();

            Devise devise = deviseRepository.findById(deviseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Devise non trouvée avec l'id: " + deviseId));

            target.setDevise(devise);
        } else {
            target.setDevise(null);
        }

        if (source.getRsAttestationRecuperee() != null) {
            int rsId = source.getRsAttestationRecuperee().getRsattestationrecupereReference();

            RsAttestationRecuperee rs = rsAttestationRecupereeRepository.findById(rsId)
                    .orElseThrow(() -> new ResourceNotFoundException("RS attestation non trouvée avec l'id: " + rsId));

            target.setRsAttestationRecuperee(rs);
        } else {
            target.setRsAttestationRecuperee(null);
        }
    }
}