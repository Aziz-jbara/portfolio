package com.app.compta.repository;

import com.app.compta.entity.TransactionBanque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionBanqueRepository extends JpaRepository<TransactionBanque, Integer> {

    @Query("""
        SELECT DISTINCT t
        FROM TransactionBanque t
        LEFT JOIN FETCH t.transactionsReleve
        LEFT JOIN FETCH t.factureClient fc
        LEFT JOIN FETCH fc.client
        LEFT JOIN FETCH fc.devise
        LEFT JOIN FETCH fc.categorie
        LEFT JOIN FETCH t.factureFrs ff
        LEFT JOIN FETCH ff.fournisseur
        LEFT JOIN FETCH ff.devise
        LEFT JOIN FETCH ff.rsAttestationRecuperee
        LEFT JOIN FETCH t.transactionEtat
        LEFT JOIN FETCH t.transactionCategorie
        LEFT JOIN FETCH t.transactionSens
        LEFT JOIN FETCH t.transactionBanqueType
        """)
    List<TransactionBanque> findAllWithRelations();

    @Query("""
        SELECT DISTINCT t
        FROM TransactionBanque t
        LEFT JOIN FETCH t.transactionsReleve
        LEFT JOIN FETCH t.factureClient fc
        LEFT JOIN FETCH fc.client
        LEFT JOIN FETCH fc.devise
        LEFT JOIN FETCH fc.categorie
        LEFT JOIN FETCH t.factureFrs ff
        LEFT JOIN FETCH ff.fournisseur
        LEFT JOIN FETCH ff.devise
        LEFT JOIN FETCH ff.rsAttestationRecuperee
        LEFT JOIN FETCH t.transactionEtat
        LEFT JOIN FETCH t.transactionCategorie
        LEFT JOIN FETCH t.transactionSens
        LEFT JOIN FETCH t.transactionBanqueType
        WHERE t.transactionsbanqueReference = :id
        """)
    TransactionBanque findByIdWithRelations(@Param("id") int id);

    @Query("""
        SELECT DISTINCT t
        FROM TransactionBanque t
        LEFT JOIN FETCH t.transactionsReleve
        LEFT JOIN FETCH t.factureClient fc
        LEFT JOIN FETCH fc.client
        LEFT JOIN FETCH fc.devise
        LEFT JOIN FETCH fc.categorie
        LEFT JOIN FETCH t.transactionEtat
        LEFT JOIN FETCH t.transactionCategorie
        LEFT JOIN FETCH t.transactionSens
        LEFT JOIN FETCH t.transactionBanqueType
        WHERE fc.facturesClientReference = :factureClientReference
        """)
    List<TransactionBanque> findByFactureClientWithRelations(@Param("factureClientReference") int factureClientReference);

    @Query("""
        SELECT DISTINCT t
        FROM TransactionBanque t
        LEFT JOIN FETCH t.transactionsReleve
        LEFT JOIN FETCH t.factureFrs ff
        LEFT JOIN FETCH ff.fournisseur
        LEFT JOIN FETCH ff.devise
        LEFT JOIN FETCH ff.rsAttestationRecuperee
        LEFT JOIN FETCH t.transactionEtat
        LEFT JOIN FETCH t.transactionCategorie
        LEFT JOIN FETCH t.transactionSens
        LEFT JOIN FETCH t.transactionBanqueType
        WHERE ff.facturesFRSReference = :factureFrsReference
        """)
    List<TransactionBanque> findByFactureFrsWithRelations(@Param("factureFrsReference") int factureFrsReference);

    @Query("""
        SELECT DISTINCT t
        FROM TransactionBanque t
        LEFT JOIN FETCH t.transactionsReleve r
        LEFT JOIN FETCH t.factureClient fc
        LEFT JOIN FETCH fc.client
        LEFT JOIN FETCH t.factureFrs ff
        LEFT JOIN FETCH ff.fournisseur
        LEFT JOIN FETCH t.transactionEtat
        LEFT JOIN FETCH t.transactionCategorie
        LEFT JOIN FETCH t.transactionSens
        LEFT JOIN FETCH t.transactionBanqueType
        WHERE r.transactionsreleveReference = :releveReference
        """)
    List<TransactionBanque> findByReleveWithRelations(@Param("releveReference") int releveReference);

    List<TransactionBanque> findByFactureClientFacturesClientReference(int factureClientReference);

    List<TransactionBanque> findByFactureFrsFacturesFRSReference(int factureFrsReference);

    List<TransactionBanque> findByTransactionsReleveTransactionsreleveReference(int releveReference);
}