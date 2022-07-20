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
public class Persona {
	
	//cascade = CascadeType.ALL -> le hereda los metodos del jpa a la tabla
	//orphanRemoval = true -> Cuando se edita, los elementos que fueron eliminados de la entity son borrados de la DB
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String imgPerfil;
    private String imgPortada;
    private String cargo;
    private String tipoCargo;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
  
    public Persona() {
    	
    }

	public Persona(Long id, String nombre, String imgPerfil, String imgPortada, String cargo, String tipoCargo,
			Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.imgPerfil = imgPerfil;
		this.imgPortada = imgPortada;
		this.cargo = cargo;
		this.tipoCargo = tipoCargo;
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

	public String getImgPerfil() {
		return imgPerfil;
	}

	public void setImgPerfil(String imgPerfil) {
		this.imgPerfil = imgPerfil;
	}

	public String getImgPortada() {
		return imgPortada;
	}

	public void setImgPortada(String imgPortada) {
		this.imgPortada = imgPortada;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}