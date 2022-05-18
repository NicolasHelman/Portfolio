package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Habilidad;
import com.PortfolioBackend.repositories.HabilidadRepository;

@Service
public class HabilidadService {

	@Autowired
	private HabilidadRepository habilidadRepository;
	
    public List<Habilidad> list() {
        return habilidadRepository.findAll();       
    }
    
    public Habilidad findById(Long id) {
    	return habilidadRepository.findById(id).orElse(null);
    }

    public void save(Habilidad habilidad) {
    	habilidadRepository.save(habilidad);
    }

    public void delete(Long id) {
    	habilidadRepository.deleteById(id);
    }
}
