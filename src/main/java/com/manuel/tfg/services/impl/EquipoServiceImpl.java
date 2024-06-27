package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEquipos;
import com.manuel.tfg.daos.RepositorioEstadisticasAtaque;
import com.manuel.tfg.daos.RepositorioJugadores;
import com.manuel.tfg.daos.RepositorioPartidos;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.daos.model.Partido;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.EquipoService;
import com.manuel.tfg.services.EstadisticasJugadorPartidoService;
import com.manuel.tfg.services.JugadoresService;
import com.manuel.tfg.services.PartidoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private RepositorioEquipos repositorioEquipos;
    private RepositorioPartidos repositorioPartidos;
    private RepositorioJugadores repositorioJugadores;
    private RepositorioEstadisticasAtaque repositorioEstadisticasZonaAtaque;

    private EstadisticasJugadorPartidoService estadisticasJugadorPartidoService;
    private JugadoresService jugadoresService;
    private PartidoService partidoService;

    public EquipoServiceImpl(RepositorioEquipos repositorioEquipos, RepositorioJugadores repositorioJugadores, RepositorioPartidos repositorioPartidos,JugadoresService jugadoresService, PartidoService partidoService, RepositorioEstadisticasAtaque repositorioEstadisticasZonaAtaque) {
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioPartidos = repositorioPartidos;
        this.jugadoresService = jugadoresService;
        this.partidoService = partidoService;
        this.repositorioEstadisticasZonaAtaque = repositorioEstadisticasZonaAtaque;
    }


    @Override
    public List<Equipo> todosEquipos() {
        List<Equipo> equipos;
        equipos = repositorioEquipos.findAll();
        return equipos;
    }

    @Override
    public void addEquipo(Equipo equipo) throws EquipoExistenteExcepcion {
        List<Equipo> listaEquipos = repositorioEquipos.findAll();
        for (Equipo equi : listaEquipos) {
            if (equipo.getNombreEquipo().equals(equi.getNombreEquipo())) {
                throw new EquipoExistenteExcepcion("El equipo con nombre " + equipo.getNombreEquipo() + " ya existe.");
            }
        }
        repositorioEquipos.save(equipo);
    }

    @Override
    @Transactional
    public void eliminarEquipo(Integer id) throws EquipoExistenteExcepcion, JugadorExistenteException {
        if (repositorioEquipos.existsById(id)) {
            // Obtener y eliminar jugadores asociados al equipo
            List<Jugador> jugadores = repositorioEquipos.findByIdEquipoIdJugador(id);
            for (Jugador jugador : jugadores) {
                jugadoresService.eliminarJugador(jugador.getIdJugador());
            }

            // Obtener y eliminar partidos asociados al equipo
            List<Partido> partidos = repositorioPartidos.findByIdPartidoIdEquipo(id);
            for (Partido partido : partidos) {
                // Obtener y eliminar estadísticas de zona de ataque asociadas al partido
                List<EstadisticasAtaque> estadisticas = repositorioEstadisticasZonaAtaque.findByIdPartido(partido.getIdPartido());
                repositorioEstadisticasZonaAtaque.deleteAll(estadisticas);

                // Eliminar el partido
                partidoService.borrarPartido(partido.getIdPartido());
            }

            // Finalmente, eliminar el equipo
            repositorioEquipos.deleteById(id);
        } else {
            throw new EquipoExistenteExcepcion("El equipo con id " + id + " no existe.");
        }
    }
    @Override
    public List<Jugador> obtenerJugadoresPorEquipo(Integer idEquipo) {
        return repositorioJugadores.findByEquipoIdEquipo(idEquipo);
    }
}





