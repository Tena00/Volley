package com.manuel.tfg.daos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Estadisticas_Zona_Campo")
public class EstadisticasZona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estadisticas_Zona_Campo")
    private Integer idEstadisticasZonaCampo;

    @ManyToOne
    @JoinColumn(name = "ID_Zona_Campo")
    private ZonasCampo idZona;

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

    public EstadisticasZona() {
    }

    public EstadisticasZona(Integer idEstadisticasZonaCampo,  int rematesTotal, int rematesPuntos, int rematesBloqueados, int saquesTotal, int saquesPuntos) {
        this.idEstadisticasZonaCampo = idEstadisticasZonaCampo;
        this.rematesTotal = rematesTotal;
        this.rematesPuntos = rematesPuntos;
        this.rematesBloqueados = rematesBloqueados;
        this.saquesTotal = saquesTotal;
        this.saquesPuntos = saquesPuntos;
    }

    public Integer getIdEstadisticasZonaCampo() {
        return idEstadisticasZonaCampo;
    }

    public void setIdEstadisticasZonaCampo(Integer idEstadisticasZonaCampo) {
        this.idEstadisticasZonaCampo = idEstadisticasZonaCampo;
    }

    public ZonasCampo getIdZona() {
        return idZona;
    }

    public void setIdZona(ZonasCampo idZona) {
        this.idZona = idZona;
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

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
