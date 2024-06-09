package com.manuel.tfg.daos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Zonas_Ataque")
@Data
public class ZonasAtaque {

    @Id
    @Column(name = "ID_Zona_Ataque")
    private Integer idZonaAtaque;
    @Column(name = "Nombre_de_la_zona")
    private String nombreZona;
    @Column(name = "Descripcion_de_la_zona")
    private String tipo;

    public ZonasAtaque() {
    }

    public ZonasAtaque(Integer idZonaAtaque, String nombreZona, String tipo) {
        this.idZonaAtaque = idZonaAtaque;
        this.nombreZona = nombreZona;
        this.tipo = tipo;
    }
}
