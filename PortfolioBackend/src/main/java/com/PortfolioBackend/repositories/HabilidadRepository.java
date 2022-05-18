package com.PortfolioBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PortfolioBackend.models.Habilidad;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

}
