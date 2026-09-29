package com.app.compta.repository;

import com.app.compta.entity.FactureFrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureFrsRepository extends JpaRepository<FactureFrs, Integer> {

    List<FactureFrs> findByFournisseurFournisseurReference(int fournisseurReference);
}