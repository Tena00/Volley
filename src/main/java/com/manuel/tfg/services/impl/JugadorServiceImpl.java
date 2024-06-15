package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEquipos;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.RepositorioJugadores;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.EquipoService;
import com.manuel.tfg.services.EstadisticasJugadorPartidoService;
import com.manuel.tfg.services.JugadoresService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadoresService {

    private RepositorioJugadores repositorioJugadores;
    private RepositorioEquipos repositorioEquipos;
    private RepositorioEstadisticas repositorioEstadisticas;

    private EstadisticasJugadorPartidoService estadisticasJugadorPartidoService;
    private EquipoService equipoService;

    public JugadorServiceImpl(RepositorioJugadores repositorioJugadores, RepositorioEquipos repositorioEquipos, RepositorioEstadisticas repositorioEstadisticas, EstadisticasJugadorPartidoService estadisticasJugadorPartidoService) {
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioEstadisticas = repositorioEstadisticas;
        this.estadisticasJugadorPartidoService = estadisticasJugadorPartidoService;

    }

    @Override
    public List<Jugador> todosJugadores() {
        List<Jugador> jugadorList;
        jugadorList = repositorioJugadores.findAll();
        return jugadorList;
    }

    @Override
    public List<Jugador> titularesJugadores(Integer idEquipo) {
        List<Jugador> jugadorList;
        List<Jugador>jugadoresEquipo = new ArrayList<>();
        jugadorList = repositorioJugadores.findAllByTitular();
        for (Jugador jugon : jugadorList) {
            if (jugon.getEquipo().getIdEquipo().equals(idEquipo)){
                jugadoresEquipo.add(jugon);
            }
        }
        return jugadoresEquipo;
    }

    @Override
    public List<Jugador> suplentesJugadores(Integer idEquipo) {
        List<Jugador> jugadorList;
        List<Jugador>jugadoresEquipo = new ArrayList<>();
        jugadorList = repositorioJugadores.findAllBySuplentes();
        for (Jugador jugon : jugadorList) {
            if (jugon.getEquipo().getIdEquipo().equals(idEquipo)){
                jugadoresEquipo.add(jugon);
            }
        }
        return jugadoresEquipo;
    }

    public Jugador actualizarEstadoJugador(Integer id, boolean titular) {
        Jugador jugador = repositorioJugadores.findById(id).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        jugador.setTitular(titular);
        return repositorioJugadores.save(jugador);
    }

    @Override
    public void addJugador(Jugador jugador) throws JugadorExistenteException {
        int titular = 0;
        List<Jugador> listaJugadores = repositorioJugadores.findAllByEquipo(jugador.getEquipo().getIdEquipo());
        for (Jugador jugon : listaJugadores) {
            if (jugon.getDorsal().equals(jugador.getDorsal())) {
                throw new JugadorExistenteException("El jugador con dorsal " + jugador.getDorsal() + " ya existe.");
            }
            if (jugon.isTitular()){
                titular = titular + 1;
            }
        }
        if (titular < 6) {
            jugador.setTitular(true);
        }else{
            jugador.setTitular(false);
        }
        repositorioJugadores.save(jugador);
    }


    @Override
    public void eliminarJugador(Integer id) throws JugadorExistenteException {
        if (repositorioJugadores.existsById(id)) {
            List<EstadisticasJugador> estadisticasJugadorList = repositorioEstadisticas.findByIdJugador(id);
            for (EstadisticasJugador estadisticasJugador : estadisticasJugadorList) {

                estadisticasJugadorPartidoService.eliminarEstadisticas(estadisticasJugador.getIdEstadisticasJugadorPartido());
            }
            repositorioJugadores.deleteById(id);
        } else {
            throw new JugadorExistenteException("El jugador con id " + id + " no existe.");
        }
    }


    @Override
    public void actualizarEquipo(Integer idJugador, Integer idEquipo) throws EquipoExistenteExcepcion, JugadorExistenteException {
        Optional<Jugador> optionalJugador = repositorioJugadores.findById(idJugador);
        if (optionalJugador.isPresent()) {
            Jugador jugador = optionalJugador.get();
            Optional<Equipo> equipoOptional = repositorioEquipos.findById(idEquipo);
            if (equipoOptional.isPresent()) {
                Equipo equipo = equipoOptional.get();
                jugador.setEquipo(equipo);
                repositorioJugadores.save(jugador);
            } else {
                throw new EquipoExistenteExcepcion("El equipo con id " + idEquipo + " no existe.");
            }
        } else {
            throw new JugadorExistenteException("El jugador con id " + idJugador + " no existe.");
        }

    }


}

