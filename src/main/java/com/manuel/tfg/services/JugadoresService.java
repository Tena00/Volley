package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;

import java.util.List;

public interface JugadoresService {

    public List<Jugador> todosJugadores();

    public List<Jugador> titularesJugadores();

    public List<Jugador> suplentesJugadores();

    public Jugador actualizarEstadoJugador(Integer id, boolean titular);

    public void addJugador(Jugador jugador) throws JugadorExistenteException;

    public void eliminarJugador(Integer id) throws JugadorExistenteException;

    public void actualizarEquipo(Integer idJugador, Integer idEquipo) throws EquipoExistenteExcepcion, JugadorExistenteException;
}
