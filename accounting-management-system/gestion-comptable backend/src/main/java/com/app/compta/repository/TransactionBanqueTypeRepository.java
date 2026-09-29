package com.app.compta.repository;

import com.app.compta.entity.TransactionBanqueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionBanqueTypeRepository extends JpaRepository<TransactionBanqueType, Integer> {
}