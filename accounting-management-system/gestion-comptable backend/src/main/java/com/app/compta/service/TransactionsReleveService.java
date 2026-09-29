package com.app.compta.service;

import com.app.compta.entity.TransactionsReleve;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TransactionsReleveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsReleveService {

    private final TransactionsReleveRepository transactionsReleveRepository;

    public List<TransactionsReleve> getAllReleves() {
        return transactionsReleveRepository.findAll();
    }

    public TransactionsReleve getReleveById(int id) {
        return transactionsReleveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relevé non trouvé avec l'id: " + id));
    }

    public List<TransactionsReleve> getRelevesByAnnee(int annee) {
        return transactionsReleveRepository.findByTransactionsreleveAnnee(annee);
    }

    public List<TransactionsReleve> getRelevesByMois(String mois) {
        return transactionsReleveRepository.findByTransactionsreleveMois(mois);
    }

    public TransactionsReleve createReleve(TransactionsReleve releve) {
        return transactionsReleveRepository.save(releve);
    }

    public TransactionsReleve updateReleve(int id, TransactionsReleve releve) {
        TransactionsReleve existing = getReleveById(id);
        existing.setTransactionsreleveAnnee(releve.getTransactionsreleveAnnee());
        existing.setTransactionsreleveMois(releve.getTransactionsreleveMois());
        existing.setTransactionsreleveEtat(releve.getTransactionsreleveEtat());
        existing.setTransactionsreleveMontant(releve.getTransactionsreleveMontant());
        return transactionsReleveRepository.save(existing);
    }

    public void deleteReleve(int id) {
        transactionsReleveRepository.deleteById(id);
    }
}