package com.app.compta.repository;

import com.app.compta.entity.TransactionCaisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionCaisseRepository extends JpaRepository<TransactionCaisse, Integer> {

    @Query("SELECT DISTINCT t FROM TransactionCaisse t " +
            "LEFT JOIN FETCH t.factureClient fc " +
            "LEFT JOIN FETCH fc.client " +
            "LEFT JOIN FETCH fc.devise " +
            "LEFT JOIN FETCH fc.categorie " +
            "LEFT JOIN FETCH t.factureFrs ff " +
            "LEFT JOIN FETCH ff.fournisseur " +
            "LEFT JOIN FETCH ff.devise " +
            "LEFT JOIN FETCH ff.rsAttestationRecuperee " +
            "LEFT JOIN FETCH t.transactionEtat " +
            "LEFT JOIN FETCH t.transactionCategorie tc " +
            "LEFT JOIN FETCH tc.transactionCategorieNature " +
            "LEFT JOIN FETCH t.transactionSens")
    List<TransactionCaisse> findAllWithRelations();

    @Query("SELECT DISTINCT t FROM TransactionCaisse t " +
            "LEFT JOIN FETCH t.factureClient fc " +
            "LEFT JOIN FETCH fc.client " +
            "LEFT JOIN FETCH fc.devise " +
            "LEFT JOIN FETCH fc.categorie " +
            "LEFT JOIN FETCH t.factureFrs ff " +
            "LEFT JOIN FETCH ff.fournisseur " +
            "LEFT JOIN FETCH ff.devise " +
            "LEFT JOIN FETCH ff.rsAttestationRecuperee " +
            "LEFT JOIN FETCH t.transactionEtat " +
            "LEFT JOIN FETCH t.transactionCategorie tc " +
            "LEFT JOIN FETCH tc.transactionCategorieNature " +
            "LEFT JOIN FETCH t.transactionSens " +
            "WHERE t.transactionscaisseReference = :id")
    Optional<TransactionCaisse> findByIdWithRelations(@Param("id") int id);

    @Query("SELECT DISTINCT t FROM TransactionCaisse t " +
            "LEFT JOIN FETCH t.factureClient fc " +
            "LEFT JOIN FETCH fc.client " +
            "LEFT JOIN FETCH fc.devise " +
            "LEFT JOIN FETCH fc.categorie " +
            "LEFT JOIN FETCH t.transactionEtat " +
            "LEFT JOIN FETCH t.transactionCategorie tc " +
            "LEFT JOIN FETCH tc.transactionCategorieNature " +
            "LEFT JOIN FETCH t.transactionSens " +
            "WHERE fc.facturesClientReference = :factureClientReference")
    List<TransactionCaisse> findByFactureClientWithRelations(
            @Param("factureClientReference") int factureClientReference
    );

    @Query("SELECT DISTINCT t FROM TransactionCaisse t " +
            "LEFT JOIN FETCH t.factureFrs ff " +
            "LEFT JOIN FETCH ff.fournisseur " +
            "LEFT JOIN FETCH ff.devise " +
            "LEFT JOIN FETCH ff.rsAttestationRecuperee " +
            "LEFT JOIN FETCH t.transactionEtat " +
            "LEFT JOIN FETCH t.transactionCategorie tc " +
            "LEFT JOIN FETCH tc.transactionCategorieNature " +
            "LEFT JOIN FETCH t.transactionSens " +
            "WHERE ff.facturesFRSReference = :factureFrsReference")
    List<TransactionCaisse> findByFactureFrsWithRelations(
            @Param("factureFrsReference") int factureFrsReference
    );

    List<TransactionCaisse> findByFactureClientFacturesClientReference(int factureClientReference);

    List<TransactionCaisse> findByFactureFrsFacturesFRSReference(int factureFrsReference);
}