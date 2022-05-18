package com.PortfolioBackend.controllers;

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
import com.PortfolioBackend.models.Persona;
import com.PortfolioBackend.services.PersonaService;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@ResponseBody
	@GetMapping("/{id}")
    public ResponseEntity<Persona> view(@PathVariable Long id) {
		Persona persona = personaService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(persona);
    }

	@PostMapping("/save")
    public ResponseEntity<Persona> save(@RequestBody Persona persona) {
		Persona p = personaService.findById(persona.getId());
		personaService.save(p);
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Persona> update(@RequestBody Persona persona) {
        personaService.save(persona); 
        
        return ResponseEntity.status(HttpStatus.OK).body(persona);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Persona> delete(@PathVariable Long id) {
    	personaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
