package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String institucion;
    private String titulo;
    private String fechaInicio;
    private String fechaFin;
    private String imgInstitucion;
    
    
    public Educacion() {
    	
    }

	public Educacion(Long id, String institucion, String titulo, String fechaInicio, String fechaFin,
			String imgInstitucion) {
		this.id = id;
		this.institucion = institucion;
		this.titulo = titulo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.imgInstitucion = imgInstitucion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getImgInstitucion() {
		return imgInstitucion;
	}

	public void setImgInstitucion(String imgInstitucion) {
		this.imgInstitucion = imgInstitucion;
	}

}
