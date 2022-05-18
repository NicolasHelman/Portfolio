package com.PortfolioBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.PortfolioBackend.models.Habilidad;
import com.PortfolioBackend.services.HabilidadService;

@RestController
@RequestMapping("/habilidades")
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadController {
	
	@Autowired
	private HabilidadService habilidadService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Habilidad> list() {
		return habilidadService.list();
    }
	
    @PostMapping("/save")
    public ResponseEntity<Habilidad> save(@RequestBody Habilidad habilidad) {
    	habilidadService.save(habilidad);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(habilidad);
        
    }
    
    @PutMapping("/update")
    public ResponseEntity<Habilidad> update(@RequestBody Habilidad habilidad) {
        habilidadService.save(habilidad); 
        
        return ResponseEntity.status(HttpStatus.OK).body(habilidad);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Habilidad> delete(@PathVariable Long id) {
    	habilidadService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
		
}