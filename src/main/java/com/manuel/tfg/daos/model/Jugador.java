package com.manuel.tfg.daos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJugador;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Dorsal")
    private Integer dorsal;
    @Column(name = "Titular")
    private Boolean titular;

    @ManyToOne
    @JoinColumn(name = "ID_Equipo")
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(Integer idJugador, String nombre, Integer dorsal, Boolean titular) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.titular = titular;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDorsal() {
        return dorsal;
    }

    public void setDorsal(Integer dorsal) {
        this.dorsal = dorsal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Boolean getTitular() {
        return titular;
    }

    public void setTitular(Boolean titular) {
        this.titular = titular;
    }

    public boolean isTitular(){
        return titular;
    }
}
