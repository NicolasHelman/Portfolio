package com.PortfolioBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PortfolioBackend.models.Social;
import com.PortfolioBackend.services.SocialService;

@RestController
@RequestMapping("/social")
@CrossOrigin(origins = "http://localhost:4200")
public class SocialController {
	
	@Autowired
	private SocialService socialService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Social> list() {
		return socialService.list();
    }
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Social> save(@RequestBody Social social) {
    	
    	socialService.save(social);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(social);
        
    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Social> update(@PathVariable Long id, @RequestBody Social s) {
        
    	Social social = socialService.findById(id);
    	
    	social.setNombre(s.getNombre());
    	social.setUrlSocial(s.getUrlSocial());
    	social.setColorSocial(s.getColorSocial());
    	
    	socialService.save(social); 
        
        return ResponseEntity.status(HttpStatus.OK).body(social);

    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Social> delete(@PathVariable Long id) {
    	socialService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
