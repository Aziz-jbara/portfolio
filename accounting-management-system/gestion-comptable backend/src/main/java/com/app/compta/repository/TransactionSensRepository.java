package com.app.compta.repository;

import com.app.compta.entity.TransactionSens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionSensRepository extends JpaRepository<TransactionSens, Integer> {
}