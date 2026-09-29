package com.app.compta.service;

import com.app.compta.entity.TransactionBanqueType;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionBanqueTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionBanqueTypeService {

    private final TransactionBanqueTypeRepository transactionBanqueTypeRepository;

    public List<TransactionBanqueType> getAllTypes() {
        return transactionBanqueTypeRepository.findAll();
    }

    public TransactionBanqueType getTypeById(int id) {
        return transactionBanqueTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type banque non trouvé avec l'id: " + id));
    }

    public TransactionBanqueType createType(TransactionBanqueType type) {
        return transactionBanqueTypeRepository.save(type);
    }

    public TransactionBanqueType updateType(int id, TransactionBanqueType type) {
        TransactionBanqueType existing = getTypeById(id);
        existing.setTransactionbanquetypesLibelle(type.getTransactionbanquetypesLibelle());
        existing.setTransactionbanquetypesDescription(type.getTransactionbanquetypesDescription());
        return transactionBanqueTypeRepository.save(existing);
    }

    public void deleteType(int id) {
        transactionBanqueTypeRepository.deleteById(id);
    }
}