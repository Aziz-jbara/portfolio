package com.app.compta.service;

import com.app.compta.entity.TransactionSens;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionSensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionSensService {

    private final TransactionSensRepository transactionSensRepository;

    public List<TransactionSens> getAllSens() {
        return transactionSensRepository.findAll();
    }

    public TransactionSens getSensById(int id) {
        return transactionSensRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sens non trouvé avec l'id: " + id));
    }

    public TransactionSens createSens(TransactionSens sens) {
        return transactionSensRepository.save(sens);
    }

    public TransactionSens updateSens(int id, TransactionSens sens) {
        TransactionSens existing = getSensById(id);
        existing.setTransactionsnatureLibelle(sens.getTransactionsnatureLibelle());
        existing.setTransactionsnatureDescription(sens.getTransactionsnatureDescription());
        return transactionSensRepository.save(existing);
    }

    public void deleteSens(int id) {
        transactionSensRepository.deleteById(id);
    }
}