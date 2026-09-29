package com.app.compta.service;

import com.app.compta.entity.*;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionBanqueService {

    private final TransactionBanqueRepository transactionBanqueRepository;

    private final TransactionsReleveRepository transactionsReleveRepository;
    private final FactureClientRepository factureClientRepository;
    private final FactureFrsRepository factureFrsRepository;
    private final TransactionEtatRepository transactionEtatRepository;
    private final TransactionCategorieRepository transactionCategorieRepository;
    private final TransactionSensRepository transactionSensRepository;
    private final TransactionBanqueTypeRepository transactionBanqueTypeRepository;

    public List<TransactionBanque> getAllTransactions() {
        return transactionBanqueRepository.findAllWithRelations();
    }

    public TransactionBanque getTransactionById(int id) {
        TransactionBanque transaction = transactionBanqueRepository.findByIdWithRelations(id);

        if (transaction == null) {
            throw new ResourceNotFoundException("Transaction banque non trouvée avec l'id: " + id);
        }

        return transaction;
    }

    public List<TransactionBanque> getTransactionsByFactureClient(int factureClientId) {
        return transactionBanqueRepository.findByFactureClientWithRelations(factureClientId);
    }

    public List<TransactionBanque> getTransactionsByFactureFrs(int factureFrsId) {
        return transactionBanqueRepository.findByFactureFrsWithRelations(factureFrsId);
    }

    public List<TransactionBanque> getTransactionsByReleve(int releveId) {
        return transactionBanqueRepository.findByReleveWithRelations(releveId);
    }

    public TransactionBanque createTransaction(TransactionBanque transaction) {
        attachRelations(transaction, transaction);
        return transactionBanqueRepository.save(transaction);
    }

    public TransactionBanque updateTransaction(int id, TransactionBanque transaction) {
        TransactionBanque existing = getTransactionById(id);

        existing.setTransactionsbanqueDateReelle(transaction.getTransactionsbanqueDateReelle());
        existing.setTransactionsbanqueDateOperation(transaction.getTransactionsbanqueDateOperation());
        existing.setTransactionsbanqueLibelle(transaction.getTransactionsbanqueLibelle());
        existing.setTransactionsbanqueMontant(transaction.getTransactionsbanqueMontant());
        existing.setTransactionsbanqueCommentaire(transaction.getTransactionsbanqueCommentaire());
        existing.setTransactionsbanqueNumeroDeType(transaction.getTransactionsbanqueNumeroDeType());

        attachRelations(existing, transaction);

        return transactionBanqueRepository.save(existing);
    }

    public void deleteTransaction(int id) {
        transactionBanqueRepository.deleteById(id);
    }

    private void attachRelations(TransactionBanque target, TransactionBanque source) {

        if (source.getTransactionsReleve() != null &&
                source.getTransactionsReleve().getTransactionsreleveReference() > 0) {

            int releveId = source.getTransactionsReleve().getTransactionsreleveReference();

            TransactionsReleve releve = transactionsReleveRepository.findById(releveId)
                    .orElseThrow(() -> new ResourceNotFoundException("Relevé non trouvé avec l'id: " + releveId));

            target.setTransactionsReleve(releve);
        } else {
            target.setTransactionsReleve(null);
        }

        if (source.getFactureClient() != null &&
                source.getFactureClient().getFacturesClientReference() > 0) {

            int factureClientId = source.getFactureClient().getFacturesClientReference();

            FactureClient factureClient = factureClientRepository.findByIdWithRelations(factureClientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Facture client non trouvée avec l'id: " + factureClientId));

            target.setFactureClient(factureClient);

            // A transaction cannot be both client and fournisseur at same time
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

            // A transaction cannot be both client and fournisseur at same time
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

        if (source.getTransactionBanqueType() != null &&
                source.getTransactionBanqueType().getTransactionbanquetypesReference() > 0) {

            int typeId = source.getTransactionBanqueType().getTransactionbanquetypesReference();

            TransactionBanqueType type = transactionBanqueTypeRepository.findById(typeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Type transaction banque non trouvé avec l'id: " + typeId));

            target.setTransactionBanqueType(type);
        } else {
            target.setTransactionBanqueType(null);
        }
    }
}