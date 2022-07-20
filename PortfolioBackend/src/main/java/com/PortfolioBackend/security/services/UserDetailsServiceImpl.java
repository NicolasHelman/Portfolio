package com.PortfolioBackend.security.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.PortfolioBackend.security.models.Usuario;
import com.PortfolioBackend.security.models.UsuarioPrincipal;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    // Cargamos un usuario por nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    	// Obtengo al usuario a traves del nombre de usuario
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
        // Obtengo los atributos del request actual
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		// Obtengo los datos de sesión de la request
		HttpSession session = attr.getRequest().getSession(true);
		// Añado un atributo a la sesión igual a mi usuario logueado
		session.setAttribute("usersession", usuario.getId());
		
        return UsuarioPrincipal.build(usuario);
    }
}
