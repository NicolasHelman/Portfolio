package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Experiencia;
import com.PortfolioBackend.repositories.ExperienciaRepository;

@Service
public class ExperienciaService {

	@Autowired
	private ExperienciaRepository experienciaRepository;
	
    public List<Experiencia> list() {
        return experienciaRepository.findAll();       
    }
    
    public Experiencia findById(Long id) {
    	return experienciaRepository.findById(id).orElse(null);
    }

    public void save(Experiencia experiencia) {
    	experienciaRepository.save(experiencia);
    }

    public void delete(Long id) {
    	experienciaRepository.deleteById(id);
    }
}
