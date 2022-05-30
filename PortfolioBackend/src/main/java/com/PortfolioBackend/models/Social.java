package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Social {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String urlSocial;
    private String colorSocial;
    
    public Social() {
    	
    }

	public Social(Long id, String nombre, String urlSocial, String colorSocial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.urlSocial = urlSocial;
		this.colorSocial = colorSocial;
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
    
}
