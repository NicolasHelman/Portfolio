package com.PortfolioBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PortfolioBackend.models.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
