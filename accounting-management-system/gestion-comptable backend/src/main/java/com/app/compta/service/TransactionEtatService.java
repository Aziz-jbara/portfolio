package com.app.compta.service;

import com.app.compta.entity.TransactionEtat;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionEtatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionEtatService {

    private final TransactionEtatRepository transactionEtatRepository;

    public List<TransactionEtat> getAllEtats() {
        return transactionEtatRepository.findAll();
    }

    public TransactionEtat getEtatById(int id) {
        return transactionEtatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etat non trouvé avec l'id: " + id));
    }

    public TransactionEtat createEtat(TransactionEtat etat) {
        return transactionEtatRepository.save(etat);
    }

    public TransactionEtat updateEtat(int id, TransactionEtat etat) {
        TransactionEtat existing = getEtatById(id);
        existing.setTransactionsetatLibelle(etat.getTransactionsetatLibelle());
        existing.setTransactionsetatDescription(etat.getTransactionsetatDescription());
        existing.setTransactionsetatOrdre(etat.getTransactionsetatOrdre());
        return transactionEtatRepository.save(existing);
    }

    public void deleteEtat(int id) {
        transactionEtatRepository.deleteById(id);
    }
}