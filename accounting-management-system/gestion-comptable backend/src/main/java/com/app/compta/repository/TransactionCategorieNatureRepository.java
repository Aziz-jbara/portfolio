package com.app.compta.repository;

import com.app.compta.entity.TransactionCategorieNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategorieNatureRepository extends JpaRepository<TransactionCategorieNature, Integer> {
}