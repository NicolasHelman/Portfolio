package com.PortfolioBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PortfolioBackend.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
