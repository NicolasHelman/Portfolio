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

import com.PortfolioBackend.models.AcercaDe;
import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.services.UsuarioService;
import com.PortfolioBackend.services.AcercaDeService;

@RestController
@RequestMapping("/acercaDe")
@CrossOrigin(origins = "http://localhost:4200")
public class AcercaDeController {
		
	@Autowired
	private AcercaDeService acercaDeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseBody
	@GetMapping("/list")
    public List<AcercaDe> list() {
		return acercaDeService.list();
    }

	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<AcercaDe> save(@RequestBody AcercaDe acercaDe) {   
		
		Usuario usuario = usuarioService.findBySesionUsuario();
		
		acercaDe.setUsuario(usuario);
    	
    	acercaDeService.save(acercaDe);
     
        return ResponseEntity.status(HttpStatus.CREATED).body(acercaDe);
        
    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AcercaDe> update(@PathVariable Long id, @RequestBody AcercaDe ad) {
    	
    	AcercaDe acercaDe = acercaDeService.findById(id);
    	
    	acercaDe.setDescripcion(ad.getDescripcion());
    	acercaDe.setFechaNacimiento(ad.getFechaNacimiento());
    	acercaDe.setCiudad(ad.getCiudad());
    	acercaDe.setTelefono(ad.getTelefono());
    	acercaDe.setMail(ad.getMail());
    	
        acercaDeService.save(acercaDe); 
        
        return ResponseEntity.status(HttpStatus.OK).body(acercaDe);

    }

	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AcercaDe> delete(@PathVariable Long id) {
    	acercaDeService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
