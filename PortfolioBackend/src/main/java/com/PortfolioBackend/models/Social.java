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
public class Social {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String urlSocial;
    private String colorSocial;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public Social() {
    	
    }

	public Social(Long id, String nombre, String urlSocial, String colorSocial, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.urlSocial = urlSocial;
		this.colorSocial = colorSocial;
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

	public String getUrlSocial() {
		return urlSocial;
	}

	public void setUrlSocial(String urlSocial) {
		this.urlSocial = urlSocial;
	}

	public String getColorSocial() {
		return colorSocial;
	}

	public void setColorSocial(String colorSocial) {
		this.colorSocial = colorSocial;
	}
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
