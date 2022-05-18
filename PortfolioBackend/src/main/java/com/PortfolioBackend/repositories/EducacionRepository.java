package com.PortfolioBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PortfolioBackend.models.Educacion;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Long>{

}
