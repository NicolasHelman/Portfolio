package com.PortfolioBackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String empresa;
    private String cargo;
    private String fechaInicio;
    private String fechaFin;
    private String imgEmpresa;
    
    public Experiencia() {
    	
    }

	public Experiencia(Long id, String empresa, String cargo, String fechaInicio, String fechaFin, String imgEmpresa) {
		this.id = id;
		this.empresa = empresa;
		this.cargo = cargo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.imgEmpresa = imgEmpresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public String getImgEmpresa() {
		return imgEmpresa;
	}

	public void setImgEmpresa(String imgEmpresa) {
		this.imgEmpresa = imgEmpresa;
	}

}
