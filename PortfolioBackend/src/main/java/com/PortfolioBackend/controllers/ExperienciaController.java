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

import com.PortfolioBackend.models.Experiencia;
import com.PortfolioBackend.services.ExperienciaService;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
	
	@Autowired
	private ExperienciaService experienciaService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Experiencia> list() {
		return experienciaService.list();
    }
	
    @PostMapping("/save")
    public ResponseEntity<Experiencia> save(@RequestBody Experiencia experiencia) {     
    	experienciaService.save(experiencia);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(experiencia);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Experiencia> update(@PathVariable Long id, @RequestBody Experiencia e) {
        
    	Experiencia experiencia = experienciaService.findById(id);
    	
    	experiencia.setEmpresa(e.getEmpresa());
    	experiencia.setCargo(e.getCargo());
    	experiencia.setFechaInicio(e.getFechaInicio());
    	experiencia.setFechaFin(e.getFechaFin());
    	experiencia.setImgEmpresa(e.getImgEmpresa());
    	
    	experienciaService.save(experiencia); 
        
        return ResponseEntity.status(HttpStatus.OK).body(experiencia);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Experiencia> delete(@PathVariable Long id) {
    	experienciaService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
	
}

