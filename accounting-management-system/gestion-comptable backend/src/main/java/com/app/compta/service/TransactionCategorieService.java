package com.app.compta.service;

import com.app.compta.entity.TransactionCategorie;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionCategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCategorieService {

    private final TransactionCategorieRepository transactionCategorieRepository;

    public List<TransactionCategorie> getAllCategories() {
        return transactionCategorieRepository.findAll();
    }

    public TransactionCategorie getCategorieById(int id) {
        return transactionCategorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id: " + id));
    }

    public List<TransactionCategorie> getCategoriesByNature(int natureId) {
        return transactionCategorieRepository
                .findByTransactionCategorieNatureTransactionscategorienatureReference(natureId);
    }

    public TransactionCategorie createCategorie(TransactionCategorie categorie) {
        return transactionCategorieRepository.save(categorie);
    }

    public TransactionCategorie updateCategorie(int id, TransactionCategorie categorie) {
        TransactionCategorie existing = getCategorieById(id);
        existing.setTransactionscategorieLibelle(categorie.getTransactionscategorieLibelle());
        existing.setTransactionscategorieDescription(categorie.getTransactionscategorieDescription());
        existing.setTransactionCategorieNature(categorie.getTransactionCategorieNature());
        return transactionCategorieRepository.save(existing);
    }

    public void deleteCategorie(int id) {
        transactionCategorieRepository.deleteById(id);
    }
}