package com.app.compta.repository;

import com.app.compta.entity.FactureClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactureClientRepository extends JpaRepository<FactureClient, Integer> {

    List<FactureClient> findByClientClientsReference(int clientsReference);

    // Load a single facture with client, devise and categorie (used by PDF and edit)
    @Query("SELECT f FROM FactureClient f " +
            "LEFT JOIN FETCH f.client " +
            "LEFT JOIN FETCH f.devise " +
            "LEFT JOIN FETCH f.categorie " +
            "WHERE f.facturesClientReference = :id")
    Optional<FactureClient> findByIdWithRelations(@Param("id") int id);

    // Load all factures with the client (used by the list)
    @Query("SELECT f FROM FactureClient f LEFT JOIN FETCH f.client")
    List<FactureClient> findAllWithClient();
}
