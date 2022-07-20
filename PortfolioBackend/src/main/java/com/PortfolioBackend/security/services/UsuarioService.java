package com.PortfolioBackend.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.repositories.UsuarioRepository;

import java.util.Optional;

import javax.servlet.http.HttpSession;

@Service
// Transactional -> es para mantener la coherencia en la base de datos
// si falla una operacion se hace un rollback y se vuelve al estado anterior
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public Usuario findById(Integer id) {
    	return usuarioRepository.findById(id).orElse(null);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
	public Usuario findBySesionUsuario() {
		
		// Obtengo los atributos del request actual
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		// Obtengo los datos de sesión de la request
		HttpSession session = attr.getRequest().getSession(true);
		// Obtengo al usuario a traves de la session
		Usuario usuario = this.findById((Integer) session.getAttribute("usersession"));
		
		return usuario;
	}

}
