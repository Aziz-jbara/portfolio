package com.app.compta.service;

import com.app.compta.entity.FactureClientCategorie;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.FactureClientCategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureClientCategorieService {

    private final FactureClientCategorieRepository factureClientCategorieRepository;

    public List<FactureClientCategorie> getAllCategories() {
        return factureClientCategorieRepository.findAll();
    }

    public FactureClientCategorie getCategorieById(int id) {
        return factureClientCategorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id: " + id));
    }

    public FactureClientCategorie createCategorie(FactureClientCategorie categorie) {
        return factureClientCategorieRepository.save(categorie);
    }

    public FactureClientCategorie updateCategorie(int id, FactureClientCategorie categorie) {
        FactureClientCategorie existing = getCategorieById(id);
        existing.setFactureclientcategorieLibelle(categorie.getFactureclientcategorieLibelle());
        existing.setFactureclientcategorieDescription(categorie.getFactureclientcategorieDescription());
        return factureClientCategorieRepository.save(existing);
    }

    public void deleteCategorie(int id) {
        factureClientCategorieRepository.deleteById(id);
    }
}