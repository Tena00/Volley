package com.manuel.tfg.daos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Estadisticas_Zona_Ataque")
@Data
public class EstadisticasAtaque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estadisticas_Zona_Ataque")
    private Integer idEstadisticasZonaAtaque;

    @ManyToOne
    @JoinColumn(name = "ID_Zona_Ataque")
    private ZonasAtaque idZonaAtaque;

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

    public EstadisticasAtaque() {
    }

    public EstadisticasAtaque(Integer idEstadisticasZonaAtaque,  int rematesTotal, int rematesPuntos, int rematesBloqueados, int saquesTotal, int saquesPuntos) {
        this.idEstadisticasZonaAtaque = idEstadisticasZonaAtaque;
        this.rematesTotal = rematesTotal;
        this.rematesPuntos = rematesPuntos;
        this.rematesBloqueados = rematesBloqueados;
        this.saquesTotal = saquesTotal;
        this.saquesPuntos = saquesPuntos;
    }

}
