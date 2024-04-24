package com.manuel.tfg.daos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipos")
public class Equipo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Equipo")
    private Integer idEquipo;
    @Column(name = "Nombre_del_equipo")
    private String nombreEquipo;

    public Equipo() {
    }

    public Equipo(Integer idEquipo, String nombreEquipo) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
