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
public class AcercaDe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    private String fechaNacimiento;
    private String ciudad;
    private String telefono;
    private String mail;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public AcercaDe() {
    	
    }

	public AcercaDe(Long id, String descripcion, String fechaNacimiento, String ciudad, String telefono, String mail,
			Usuario usuario) {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaNacimiento = fechaNacimiento;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.mail = mail;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
