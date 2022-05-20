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

import com.PortfolioBackend.models.Proyecto;
import com.PortfolioBackend.services.ProyectoService;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

	@Autowired
	private ProyectoService proyectoService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Proyecto> list() {
		return proyectoService.list();
    }
	
    @PostMapping("/save")
    public ResponseEntity<Proyecto> save(@RequestBody Proyecto proyecto) {    
    	proyectoService.save(proyecto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Proyecto> update(@PathVariable Long id, @RequestBody Proyecto p) {
        
    	Proyecto proyecto = proyectoService.findById(id);
    	
    	proyecto.setNombre(p.getNombre());
    	proyecto.setDescripcion(p.getDescripcion());
    	proyecto.setImgProyecto(p.getImgProyecto());
    	proyecto.setUrlProyecto(p.getUrlProyecto());
    	
    	proyectoService.save(proyecto); 
        
        return ResponseEntity.status(HttpStatus.OK).body(proyecto);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id) {
    	proyectoService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
