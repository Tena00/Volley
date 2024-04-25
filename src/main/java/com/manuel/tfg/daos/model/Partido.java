package com.manuel.tfg.daos.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Partido")
    private Integer idPartido;

    @Column(name = "Resultado_del_partido")
    private String resultadoPartido;

    @ManyToOne
    @JoinColumn(name = "Equipo_ID")
    private Equipo equipo;

    public Partido() {
    }

    public Partido(Integer idPartido, Equipo equipo, String resultadoPartido) {
        this.idPartido = idPartido;
        this.equipo = equipo;
        this.resultadoPartido = resultadoPartido;
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getResultadoPartido() {
        return resultadoPartido;
    }

    public void setResultadoPartido(String resultadoPartido) {
        this.resultadoPartido = resultadoPartido;
    }
}
