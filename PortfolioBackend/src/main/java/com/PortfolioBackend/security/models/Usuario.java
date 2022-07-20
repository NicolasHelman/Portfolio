package com.PortfolioBackend.security.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.PortfolioBackend.models.AcercaDe;
import com.PortfolioBackend.models.Contacto;
import com.PortfolioBackend.models.Educacion;
import com.PortfolioBackend.models.Experiencia;
import com.PortfolioBackend.models.Habilidad;
import com.PortfolioBackend.models.Persona;
import com.PortfolioBackend.models.Proyecto;
import com.PortfolioBackend.models.Social;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    // Relacion muchos a muchos
    // un usuario puede tener varios roles
    // y un rol puede pertenecer a varios usuario
    // se crea una tabla intermedia
    // contiene dos campos (id del usuario y id del rol)
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Persona> persona;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AcercaDe> acercaDe;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Educacion> educacion;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experiencia> experiencia;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Habilidad> habilidad;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Proyecto> proyecto;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Social> social;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contacto> contacto;

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}

}
