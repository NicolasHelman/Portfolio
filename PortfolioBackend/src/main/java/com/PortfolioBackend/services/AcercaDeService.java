package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.AcercaDe;
import com.PortfolioBackend.repositories.AcercaDeRepository;

@Service
public class AcercaDeService {

	@Autowired
	private AcercaDeRepository acercaDeRepository;
	
    public List<AcercaDe> list() {
        return acercaDeRepository.findAll();       
    }
    
    public AcercaDe findById(Long id) {
    	return acercaDeRepository.findById(id).orElse(null);
    }

    public void save(AcercaDe acercaDe) {
        acercaDeRepository.save(acercaDe);
    }

    public void delete(Long id) {
    	acercaDeRepository.deleteById(id);
    }
}
