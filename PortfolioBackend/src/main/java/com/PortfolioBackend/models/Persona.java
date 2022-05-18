package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Persona {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String imgPerfil;
    private String imgPortada;
    private String cargo;
    private String tipoCargo;
     
    public Persona() {
    	
    }

	public Persona(Long id, String nombre, String imgPerfil, String imgPortada, String cargo, String tipoCargo) {
		this.id = id;
		this.nombre = nombre;
		this.imgPerfil = imgPerfil;
		this.imgPortada = imgPortada;
		this.cargo = cargo;
		this.tipoCargo = tipoCargo;
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
	

}