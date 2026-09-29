package com.app.compta.service;

import com.app.compta.entity.FactureClient;
import com.app.compta.entity.FactureFrs;
import com.app.compta.entity.TransactionCaisse;
import com.app.compta.entity.TransactionCategorie;
import com.app.compta.entity.TransactionEtat;
import com.app.compta.entity.TransactionSens;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.FactureClientRepository;
import com.app.compta.repository.FactureFrsRepository;
import com.app.compta.repository.TransactionCaisseRepository;
import com.app.compta.repository.TransactionCategorieRepository;
import com.app.compta.repository.TransactionEtatRepository;
import com.app.compta.repository.TransactionSensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCaisseService {

    private final TransactionCaisseRepository transactionCaisseRepository;
    private final FactureClientRepository factureClientRepository;
    private final FactureFrsRepository factureFrsRepository;
    private final TransactionEtatRepository transactionEtatRepository;
    private final TransactionCategorieRepository transactionCategorieRepository;
    private final TransactionSensRepository transactionSensRepository;

    public List<TransactionCaisse> getAllTransactions() {
        return transactionCaisseRepository.findAllWithRelations();
    }

    public TransactionCaisse getTransactionById(int id) {
        return transactionCaisseRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction caisse non trouvée avec l'id: " + id));
    }

    public List<TransactionCaisse> getTransactionsByFactureClient(int factureClientId) {
        return transactionCaisseRepository.findByFactureClientWithRelations(factureClientId);
    }

    public List<TransactionCaisse> getTransactionsByFactureFrs(int factureFrsId) {
        return transactionCaisseRepository.findByFactureFrsWithRelations(factureFrsId);
    }

    public TransactionCaisse createTransaction(TransactionCaisse transaction) {
        attachRelations(transaction, transaction);

        TransactionCaisse saved = transactionCaisseRepository.save(transaction);

        return getTransactionById(saved.getTransactionscaisseReference());
    }

    public TransactionCaisse updateTransaction(int id, TransactionCaisse transaction) {
        TransactionCaisse existing = getTransactionById(id);

        existing.setTransactionscaisseDate(transaction.getTransactionscaisseDate());
        existing.setTransactionscaisseLibelle(transaction.getTransactionscaisseLibelle());
        existing.setTransactionscaisseNumPieceComptable(transaction.getTransactionscaisseNumPieceComptable());
        existing.setTransactionscaisseMontant(transaction.getTransactionscaisseMontant());
        existing.setTransactionscaisseCommentaire(transaction.getTransactionscaisseCommentaire());
        existing.setTransactionscaisseSoldeProgressif(transaction.getTransactionscaisseSoldeProgressif());
        existing.setTransactionscaisseCompteCourantAssocie(transaction.getTransactionscaisseCompteCourantAssocie());

        attachRelations(existing, transaction);

        TransactionCaisse saved = transactionCaisseRepository.save(existing);

        return getTransactionById(saved.getTransactionscaisseReference());
    }

    public void deleteTransaction(int id) {
        transactionCaisseRepository.deleteById(id);
    }

    private void attachRelations(TransactionCaisse target, TransactionCaisse source) {

        if (source.getFactureClient() != null &&
                source.getFactureClient().getFacturesClientReference() > 0) {

            int factureClientId = source.getFactureClient().getFacturesClientReference();

            FactureClient factureClient = factureClientRepository.findByIdWithRelations(factureClientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Facture client non trouvée avec l'id: " + factureClientId));

            target.setFactureClient(factureClient);

            // Une transaction caisse ne doit pas être client et fournisseur en même temps
            target.setFactureFrs(null);

        } else {
            target.setFactureClient(null);
        }

        if (source.getFactureFrs() != null &&
                source.getFactureFrs().getFacturesFRSReference() > 0) {

            int factureFrsId = source.getFactureFrs().getFacturesFRSReference();

            FactureFrs factureFrs = factureFrsRepository.findById(factureFrsId)
                    .orElseThrow(() -> new ResourceNotFoundException("Facture fournisseur non trouvée avec l'id: " + factureFrsId));

            target.setFactureFrs(factureFrs);

            // Une transaction caisse ne doit pas être client et fournisseur en même temps
            target.setFactureClient(null);

        } else {
            if (source.getFactureClient() == null) {
                target.setFactureFrs(null);
            }
        }

        if (source.getTransactionEtat() != null &&
                source.getTransactionEtat().getTransactionsetatReference() > 0) {

            int etatId = source.getTransactionEtat().getTransactionsetatReference();

            TransactionEtat etat = transactionEtatRepository.findById(etatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Etat transaction non trouvé avec l'id: " + etatId));

            target.setTransactionEtat(etat);

        } else {
            target.setTransactionEtat(null);
        }

        if (source.getTransactionCategorie() != null &&
                source.getTransactionCategorie().getTransactionscategorieReference() > 0) {

            int categorieId = source.getTransactionCategorie().getTransactionscategorieReference();

            TransactionCategorie categorie = transactionCategorieRepository.findById(categorieId)
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie transaction non trouvée avec l'id: " + categorieId));

            target.setTransactionCategorie(categorie);

        } else {
            target.setTransactionCategorie(null);
        }

        if (source.getTransactionSens() != null &&
                source.getTransactionSens().getTransactionsnatureReference() > 0) {

            int sensId = source.getTransactionSens().getTransactionsnatureReference();

            TransactionSens sens = transactionSensRepository.findById(sensId)
                    .orElseThrow(() -> new ResourceNotFoundException("Sens transaction non trouvé avec l'id: " + sensId));

            target.setTransactionSens(sens);

        } else {
            target.setTransactionSens(null);
        }
    }
}