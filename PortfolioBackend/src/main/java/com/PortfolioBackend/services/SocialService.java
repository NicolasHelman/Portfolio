package com.PortfolioBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Social;
import com.PortfolioBackend.repositories.SocialRepository;

@Service
public class SocialService {

	@Autowired
	private SocialRepository socialRepository;
	
    public List<Social> list() {
        return socialRepository.findAll();       
    }
    
    public Social findById(Long id) {
    	return socialRepository.findById(id).orElse(null);
    }

    public void save(Social social) {
    	socialRepository.save(social);
    }

    public void delete(Long id) {
    	socialRepository.deleteById(id);
    }
}
