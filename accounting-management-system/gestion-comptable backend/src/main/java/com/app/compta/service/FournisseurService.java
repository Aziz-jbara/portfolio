package com.app.compta.service;

import com.app.compta.entity.Fournisseur;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurById(int id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé avec l'id: " + id));
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public Fournisseur updateFournisseur(int id, Fournisseur fournisseur) {
        Fournisseur existing = getFournisseurById(id);
        existing.setFournisseurRaisonSocial(fournisseur.getFournisseurRaisonSocial());
        existing.setFournisseurAdresse(fournisseur.getFournisseurAdresse());
        existing.setFournisseurCodePostal(fournisseur.getFournisseurCodePostal());
        existing.setFournisseurTelephone(fournisseur.getFournisseurTelephone());
        existing.setFournisseurCodeTVA(fournisseur.getFournisseurCodeTVA());
        existing.setFournisseursGouvernorat(fournisseur.getFournisseursGouvernorat());
        existing.setFournisseursPays(fournisseur.getFournisseursPays());
        existing.setFournisseursFax(fournisseur.getFournisseursFax());
        return fournisseurRepository.save(existing);
    }

    public void deleteFournisseur(int id) {
        fournisseurRepository.deleteById(id);
    }
}