package com.app.compta.service;

import com.app.compta.entity.TransactionCategorieNature;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionCategorieNatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCategorieNatureService {

    private final TransactionCategorieNatureRepository transactionCategorieNatureRepository;

    public List<TransactionCategorieNature> getAllNatures() {
        return transactionCategorieNatureRepository.findAll();
    }

    public TransactionCategorieNature getNatureById(int id) {
        return transactionCategorieNatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nature non trouvée avec l'id: " + id));
    }

    public TransactionCategorieNature createNature(TransactionCategorieNature nature) {
        return transactionCategorieNatureRepository.save(nature);
    }

    public TransactionCategorieNature updateNature(int id, TransactionCategorieNature nature) {
        TransactionCategorieNature existing = getNatureById(id);
        existing.setTransactionscategorienatureLibelle(nature.getTransactionscategorienatureLibelle());
        return transactionCategorieNatureRepository.save(existing);
    }

    public void deleteNature(int id) {
        transactionCategorieNatureRepository.deleteById(id);
    }
}