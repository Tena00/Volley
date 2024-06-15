package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEquipos;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.RepositorioJugadores;
import com.manuel.tfg.daos.RepositorioPartidos;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Partido;
import com.manuel.tfg.services.PartidoService;
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

    public PartidoServiceImpl(RepositorioPartidos repositorioPartidos, RepositorioEquipos repositorioEquipos, RepositorioJugadores repositorioJugadores, RepositorioEstadisticas repositorioEstadisticas) {
        this.repositorioPartidos = repositorioPartidos;
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioEstadisticas = repositorioEstadisticas;
    }


    @Override
    public List<Partido> todosPartidos() {
        return repositorioPartidos.findAll();
    }

    @Override
    public void iniciarPartido(Integer idEquipo) {
        Optional<Equipo> equipo = repositorioEquipos.findById(idEquipo);
        if (!ObjectUtils.isEmpty(equipo)) {
            Equipo equipo1 = equipo.get();
            Partido partido = new Partido();
            partido.setEquipo(equipo1);
            repositorioPartidos.save(partido);
        }
    }

    @Override
    public List<EstadisticasJugador> estadisticasPartido(Integer idPartido) {
        return repositorioEstadisticas.findByIdPartido(idPartido);
    }

    @Override
    public void borrarPartido(Integer idPartido) {
        repositorioPartidos.deleteById(idPartido);
    }
}
