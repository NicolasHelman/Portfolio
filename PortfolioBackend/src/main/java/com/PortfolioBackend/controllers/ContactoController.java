package com.PortfolioBackend.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PortfolioBackend.models.Contacto;
import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.services.UsuarioService;
import com.PortfolioBackend.services.ContactoService;

@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactoController {
	
	@Autowired
	private ContactoService contactoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/save")
	public ResponseEntity<Contacto> enviar(@RequestBody Contacto contacto) throws UnsupportedEncodingException, MessagingException {
		
		Usuario usuario = usuarioService.findBySesionUsuario();
		
		contacto.setUsuario(usuario);
		
		contactoService.save(contacto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contacto);

	}

}
