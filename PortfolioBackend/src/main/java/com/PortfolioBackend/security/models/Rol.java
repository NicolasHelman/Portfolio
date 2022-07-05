package com.PortfolioBackend.security.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.PortfolioBackend.security.enums.RolNombre;


@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    // hay que aclarar que el enum es tipo cadena con EnumType.STRING (pq sino por defecto la bd crea tipo int)
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
 
}
