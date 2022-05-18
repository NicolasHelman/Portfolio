package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Proyecto;
import com.PortfolioBackend.repositories.ProyectoRepository;

@Service
public class ProyectoService {

	@Autowired
	private ProyectoRepository proyectoRepository;
	
    public List<Proyecto> list() {
        return proyectoRepository.findAll();       
    }
    
    public Proyecto findById(Long id) {
    	return proyectoRepository.findById(id).orElse(null);
    }

    public void save(Proyecto proyecto) {
    	proyectoRepository.save(proyecto);
    }

    public void delete(Long id) {
    	proyectoRepository.deleteById(id);
    }
}
