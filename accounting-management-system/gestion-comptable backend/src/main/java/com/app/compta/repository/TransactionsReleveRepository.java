package com.app.compta.repository;

import com.app.compta.entity.TransactionsReleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsReleveRepository extends JpaRepository<TransactionsReleve, Integer> {

    List<TransactionsReleve> findByTransactionsreleveAnnee(int transactionsreleveAnnee);

    List<TransactionsReleve> findByTransactionsreleveMois(String transactionsreleveMois);

}