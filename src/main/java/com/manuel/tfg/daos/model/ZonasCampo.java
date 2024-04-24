package com.manuel.tfg.daos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Zonas_Campo")
@Data
public class ZonasCampo {

    @Id
    @Column(name = "Nombre_de_la_zona")
    private String nombreZona;
    @Column(name = "Descripcion_de_la_zona")
    private String tipo;

    public ZonasCampo() {
    }

    public ZonasCampo(String nombreZona, String tipo) {
        this.nombreZona = nombreZona;
        this.tipo = tipo;
    }
}
