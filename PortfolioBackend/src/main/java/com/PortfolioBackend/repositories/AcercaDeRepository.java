package com.PortfolioBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PortfolioBackend.models.AcercaDe;

@Repository
public interface AcercaDeRepository extends JpaRepository<AcercaDe, Long> {

}
