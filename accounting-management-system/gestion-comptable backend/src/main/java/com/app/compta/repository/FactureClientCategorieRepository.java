package com.app.compta.repository;

import com.app.compta.entity.FactureClientCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureClientCategorieRepository extends JpaRepository<FactureClientCategorie, Integer> {
}