package com.manuel.tfg.daos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Acciones_Ataque")
public class Acciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Accion_Ataque")
    private Integer idAccion;

    @ManyToOne
    @JoinColumn(name = "ID_Estadisticas_Jugador_Partido")
    private EstadisticasJugador estadisticasJugador;

    @ManyToOne
    @JoinColumn(name = "ID_Estadisticas_Zona_Campo")
    private EstadisticasZona estadisticasZona;

    @ManyToOne
    @JoinColumn(name = "ID_Partido")
    private Partido partido;

    @Column(name = "Color_Resultado_Accion")
    private String coloResultado;

    @ManyToOne
    @JoinColumn(name = "ID_Estadisticas_Zona_Ataque")
    private EstadisticasAtaque estadisticasAtaque;

    public Acciones() {
    }

    public Acciones(Integer idAccion, EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, String coloResultado, EstadisticasAtaque estadisticasAtaque, Partido partido) {
        this.idAccion = idAccion;
        this.estadisticasJugador = estadisticasJugador;
        this.estadisticasZona = estadisticasZona;
        this.coloResultado = coloResultado;
        this.partido = partido;
        this.estadisticasAtaque = estadisticasAtaque;

    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public EstadisticasJugador getEstadisticasJugador() {
        return estadisticasJugador;
    }

    public void setEstadisticasJugador(EstadisticasJugador estadisticasJugador) {
        this.estadisticasJugador = estadisticasJugador;
    }

    public EstadisticasZona getEstadisticasZona() {
        return estadisticasZona;
    }

    public void setEstadisticasZona(EstadisticasZona estadisticasZona) {
        this.estadisticasZona = estadisticasZona;
    }

    public String getColoResultado() {
        return coloResultado;
    }

    public void setColoResultado(String coloResultado) {
        this.coloResultado = coloResultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EstadisticasAtaque getEstadisticasAtaque() {
        return estadisticasAtaque;
    }

    public void setEstadisticasAtaque(EstadisticasAtaque estadisticasAtaque) {
        this.estadisticasAtaque = estadisticasAtaque;
    }
}
