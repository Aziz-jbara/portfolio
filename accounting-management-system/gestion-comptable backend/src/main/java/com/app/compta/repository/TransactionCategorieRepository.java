package com.app.compta.repository;

import com.app.compta.entity.TransactionCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionCategorieRepository extends JpaRepository<TransactionCategorie, Integer> {

    List<TransactionCategorie> findByTransactionCategorieNatureTransactionscategorienatureReference(
            int transactionscategorienatureReference
    );

}