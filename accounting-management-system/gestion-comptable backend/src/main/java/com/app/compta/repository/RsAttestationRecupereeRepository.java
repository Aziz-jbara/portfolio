package com.app.compta.repository;

import com.app.compta.entity.RsAttestationRecuperee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsAttestationRecupereeRepository extends JpaRepository<RsAttestationRecuperee, Integer> {
}