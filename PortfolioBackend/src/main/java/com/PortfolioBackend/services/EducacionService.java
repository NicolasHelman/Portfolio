package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Educacion;
import com.PortfolioBackend.repositories.EducacionRepository;

@Service
public class EducacionService {

	@Autowired
	private EducacionRepository educacionRepository;
	
    public List<Educacion> list() {
        return educacionRepository.findAll();       
    }
    
    public Educacion findById(Long id) {
    	return educacionRepository.findById(id).orElse(null);
    }

    public void save(Educacion educacion) {
    	educacionRepository.save(educacion);
    }

    public void delete(Long id) {
    	educacionRepository.deleteById(id);
    }
}
