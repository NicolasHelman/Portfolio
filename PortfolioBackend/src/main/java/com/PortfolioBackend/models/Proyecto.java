package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;
    private String imgProyecto;
    private String urlProyecto;
    
    public Proyecto() {
    	
    }

	public Proyecto(Long id, String nombre, String descripcion, String imgProyecto, String urlProyecto) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imgProyecto = imgProyecto;
		this.urlProyecto = urlProyecto;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImgProyecto() {
		return imgProyecto;
	}

	public void setImgProyecto(String imgProyecto) {
		this.imgProyecto = imgProyecto;
	}

	public String getUrlProyecto() {
		return urlProyecto;
	}

	public void setUrlProyecto(String urlProyecto) {
		this.urlProyecto = urlProyecto;
	}

}
