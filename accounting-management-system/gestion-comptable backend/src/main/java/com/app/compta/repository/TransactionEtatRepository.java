package com.app.compta.repository;

import com.app.compta.entity.TransactionEtat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEtatRepository extends JpaRepository<TransactionEtat, Integer> {
}