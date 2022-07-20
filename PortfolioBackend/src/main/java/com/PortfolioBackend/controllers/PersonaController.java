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

import com.PortfolioBackend.models.Persona;
import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.services.UsuarioService;
import com.PortfolioBackend.services.PersonaService;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<Persona> list() {
		return personaService.list();
    }

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
    public ResponseEntity<Persona> save(@RequestBody Persona persona) {
	
		Usuario usuario = usuarioService.findBySesionUsuario();
		
		persona.setUsuario(usuario);
		
		personaService.save(persona);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }
    
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
    public ResponseEntity<Persona> update(@PathVariable Long id, @RequestBody Persona p) {
        
		Persona persona = personaService.findById(id);
		
		persona.setNombre(p.getNombre());
		persona.setImgPerfil(p.getImgPerfil());
		persona.setImgPortada(p.getImgPortada());
		persona.setCargo(p.getCargo());
		persona.setTipoCargo(p.getTipoCargo());

		personaService.save(persona);
        
        return ResponseEntity.status(HttpStatus.OK).body(persona);
    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id) {
    	personaService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
