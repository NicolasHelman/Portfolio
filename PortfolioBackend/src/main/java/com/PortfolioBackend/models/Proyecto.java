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
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;
    private String imgProyecto;
    private String urlProyecto;
    private String githubProyecto;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
    
    public Proyecto() {
    	
    }

	public Proyecto(Long id, String nombre, String descripcion, String imgProyecto, String urlProyecto,
			String githubProyecto, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imgProyecto = imgProyecto;
		this.urlProyecto = urlProyecto;
		this.githubProyecto = githubProyecto;
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

	public String getGithubProyecto() {
		return githubProyecto;
	}

	public void setGithubProyecto(String githubProyecto) {
		this.githubProyecto = githubProyecto;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
