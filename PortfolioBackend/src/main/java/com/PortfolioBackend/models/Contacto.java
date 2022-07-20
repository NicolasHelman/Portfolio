package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.PortfolioBackend.security.models.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Contacto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String mail;
    private String asunto;
    private String mensaje;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public Contacto() {
    	
    }

	public Contacto(Long id, String nombre, String mail, String asunto, String mensaje, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}   
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
