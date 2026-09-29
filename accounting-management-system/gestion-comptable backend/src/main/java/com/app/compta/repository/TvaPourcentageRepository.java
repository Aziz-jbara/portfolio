package com.app.compta.repository;

import com.app.compta.entity.TvaPourcentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvaPourcentageRepository extends JpaRepository<TvaPourcentage, Integer> {
}