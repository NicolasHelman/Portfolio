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
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String empresa;
    private String cargo;
    private String fechaInicio;
    private String fechaFin;
    private String imgEmpresa;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public Experiencia() {
    	
    }

	public Experiencia(Long id, String empresa, String cargo, String fechaInicio, String fechaFin, String imgEmpresa,
			Usuario usuario) {
		this.id = id;
		this.empresa = empresa;
		this.cargo = cargo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.imgEmpresa = imgEmpresa;
		this.usuario = usuario;
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
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
