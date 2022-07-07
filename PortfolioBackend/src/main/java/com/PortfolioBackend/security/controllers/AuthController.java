package com.PortfolioBackend.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.PortfolioBackend.errors.Mensaje;
import com.PortfolioBackend.security.dtos.JwtDto;
import com.PortfolioBackend.security.dtos.LoginUsuario;
import com.PortfolioBackend.security.dtos.NuevoUsuario;
import com.PortfolioBackend.security.enums.RolNombre;
import com.PortfolioBackend.security.jwt.JwtProvider;
import com.PortfolioBackend.security.models.Rol;
import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.services.RolService;
import com.PortfolioBackend.security.services.UsuarioService;

import javax.validation.Valid;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

	@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Ha ocurrido un error"));
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Nombre de usuario no disponible"));
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Email no disponible"));

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
    
        usuarioService.save(usuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario registrado correctamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
    	  
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        JwtDto jwtDto = new JwtDto(jwt);
        
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
    	
        String token = jwtProvider.refreshToken(jwtDto);
        
        JwtDto jwt = new JwtDto(token);
        
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }
    
}
