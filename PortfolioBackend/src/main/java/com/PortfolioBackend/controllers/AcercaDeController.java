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

import com.PortfolioBackend.models.AcercaDe;
import com.PortfolioBackend.services.AcercaDeService;

@RestController
@RequestMapping("/acercaDe")
@CrossOrigin(origins = "http://localhost:4200")
public class AcercaDeController {
		
	@Autowired
	private AcercaDeService acercaDeService;
	
	@ResponseBody
	@GetMapping("/{id}")
    public ResponseEntity<AcercaDe> view(@PathVariable Long id) {
		AcercaDe acercaDe = acercaDeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(acercaDe);
    }
	
	@ResponseBody
	@GetMapping("/list")
    public List<AcercaDe> list() {
		return acercaDeService.list();
    }

    @PostMapping("/save")
    public ResponseEntity<AcercaDe> save(@RequestBody AcercaDe acercaDe) {   
    	acercaDeService.save(acercaDe);
     
        return ResponseEntity.status(HttpStatus.CREATED).body(acercaDe);
        
    }
    
    @PutMapping("/update")
    public ResponseEntity<AcercaDe> update(@RequestBody AcercaDe acercaDe) {
        acercaDeService.save(acercaDe); 
        
        return ResponseEntity.status(HttpStatus.OK).body(acercaDe);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AcercaDe> delete(@PathVariable Long id) {
    	acercaDeService.delete(id);
    	
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
