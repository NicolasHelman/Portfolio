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
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private Integer porcentaje;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public Habilidad() {
    	
    }

	public Habilidad(Long id, String tipo, Integer porcentaje, Usuario usuario) {
		this.id = id;
		this.tipo = tipo;
		this.porcentaje = porcentaje;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
