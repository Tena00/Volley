package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;

import java.util.List;

public interface EquipoService {

    public List<Equipo> todosEquipos();

    public void addEquipo(Equipo equipo) throws EquipoExistenteExcepcion;

    public void eliminarEquipo(Integer id) throws EquipoExistenteExcepcion, JugadorExistenteException;

    public List<Jugador> obtenerJugadoresPorEquipo(Integer idEquipo);
}
