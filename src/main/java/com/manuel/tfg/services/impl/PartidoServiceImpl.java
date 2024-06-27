package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.*;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Partido;
import com.manuel.tfg.services.PartidoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartidoServiceImpl implements PartidoService {

    private RepositorioPartidos repositorioPartidos;
    private RepositorioEquipos repositorioEquipos;
    private RepositorioJugadores repositorioJugadores;
    private RepositorioEstadisticas repositorioEstadisticas;
    private RepositorioEstadisticasAtaque repositorioEstadisticasZonaAtaque;

    public PartidoServiceImpl(RepositorioPartidos repositorioPartidos, RepositorioEquipos repositorioEquipos, RepositorioJugadores repositorioJugadores, RepositorioEstadisticas repositorioEstadisticas, RepositorioEstadisticasAtaque repositorioEstadisticasZonaAtaque) {
        this.repositorioPartidos = repositorioPartidos;
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioEstadisticas = repositorioEstadisticas;
        this.repositorioEstadisticasZonaAtaque = repositorioEstadisticasZonaAtaque;
    }


    @Override
    public List<Partido> todosPartidos() {
        return repositorioPartidos.findAll();
    }

    @Override
    public Integer iniciarPartido(Integer idEquipo) {
        Optional<Equipo> equipo = repositorioEquipos.findById(idEquipo);
        if (!ObjectUtils.isEmpty(equipo)) {
            Equipo equipo1 = equipo.get();
            Partido partido = new Partido();
            partido.setEquipo(equipo1);
            repositorioPartidos.save(partido);
            return partido.getIdPartido();
        }
        return null;
    }

    @Override
    public List<EstadisticasJugador> estadisticasPartido(Integer idPartido) {
        return repositorioEstadisticas.findByIdPartido(idPartido);
    }

    @Override
    @Transactional
    public void borrarPartido(Integer idPartido) {
        if (repositorioPartidos.existsById(idPartido)) {
            // Obtener y eliminar estadísticas de zona de ataque asociadas al partido
            List<EstadisticasAtaque> estadisticas = repositorioEstadisticasZonaAtaque.findByIdPartido(idPartido);
            repositorioEstadisticasZonaAtaque.deleteAll(estadisticas);

            // Eliminar el partido
            repositorioPartidos.deleteById(idPartido);
        }
    }
}
