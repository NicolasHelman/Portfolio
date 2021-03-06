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

import com.PortfolioBackend.models.Educacion;
import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.services.UsuarioService;
import com.PortfolioBackend.services.EducacionService;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {
		
	@Autowired
	private EducacionService educacionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Educacion> list() {
		return educacionService.list();
    }
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Educacion> save(@RequestBody Educacion educacion) {    
		
		Usuario usuario = usuarioService.findBySesionUsuario();
		
		educacion.setUsuario(usuario);
    	
    	educacionService.save(educacion);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(educacion);
        
    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Educacion> update(@PathVariable Long id, @RequestBody Educacion e) {
    	
    	Educacion educacion = educacionService.findById(id);
    	
    	educacion.setInstitucion(e.getInstitucion());
    	educacion.setTitulo(e.getTitulo());
    	educacion.setFechaInicio(e.getFechaInicio());
    	educacion.setFechaFin(e.getFechaFin());
    	educacion.setImgInstitucion(e.getImgInstitucion());
    	
    	educacionService.save(educacion);
        
        return ResponseEntity.status(HttpStatus.OK).body(educacion);

    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Educacion> delete(@PathVariable Long id) {
    	educacionService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
	
}

