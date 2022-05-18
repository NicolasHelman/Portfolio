package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Persona;
import com.PortfolioBackend.repositories.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

    public List<Persona> list() {
        return personaRepository.findAll();       
    }
    
    public Persona findById(Long id) {
    	return personaRepository.findById(id).orElse(null);
    }

    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    public void delete(Long id) {
    	personaRepository.deleteById(id);
    }

}
