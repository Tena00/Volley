package com.manuel.tfg.daos.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Estadisticas_Jugador_Partido")
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estadisticas")
    private Integer idEstadisticasJugadorPartido;

    @ManyToOne
    @JoinColumn(name = "ID_Jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "ID_Partido")
    private Partido partido;

    @Column(name = "Remates_Total")
    private int rematesTotal;

    @Column(name = "Remates_Puntos")
    private int rematesPuntos;

    @Column(name = "Remates_Bloqueados")
    private int rematesBloqueados;

    @Column(name = "Saques_Total")
    private int saquesTotal;

    @Column(name = "Saques_Puntos")
    private int saquesPuntos;

    public EstadisticasJugador() {
    }

    public EstadisticasJugador(Integer idEstadisticasJugadorPartido, int rematesTotal, int rematesPuntos, int rematesBloqueados, int saquesTotal, int saquesPuntos) {
        this.idEstadisticasJugadorPartido = idEstadisticasJugadorPartido;
        this.rematesTotal = rematesTotal;
        this.rematesPuntos = rematesPuntos;
        this.rematesBloqueados = rematesBloqueados;
        this.saquesTotal = saquesTotal;
        this.saquesPuntos = saquesPuntos;
    }

    public Integer getIdEstadisticasJugadorPartido() {
        return idEstadisticasJugadorPartido;
    }

    public void setIdEstadisticasJugadorPartido(Integer idEstadisticasJugadorPartido) {
        this.idEstadisticasJugadorPartido = idEstadisticasJugadorPartido;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public int getRematesTotal() {
        return rematesTotal;
    }

    public void setRematesTotal(int rematesTotal) {
        this.rematesTotal = rematesTotal;
    }

    public int getRematesPuntos() {
        return rematesPuntos;
    }

    public void setRematesPuntos(int rematesPuntos) {
        this.rematesPuntos = rematesPuntos;
    }

    public int getRematesBloqueados() {
        return rematesBloqueados;
    }

    public void setRematesBloqueados(int rematesBloqueados) {
        this.rematesBloqueados = rematesBloqueados;
    }

    public int getSaquesTotal() {
        return saquesTotal;
    }

    public void setSaquesTotal(int saquesTotal) {
        this.saquesTotal = saquesTotal;
    }

    public int getSaquesPuntos() {
        return saquesPuntos;
    }

    public void setSaquesPuntos(int saquesPuntos) {
        this.saquesPuntos = saquesPuntos;
    }
}
