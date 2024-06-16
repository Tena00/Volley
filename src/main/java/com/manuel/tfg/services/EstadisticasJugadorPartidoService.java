package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.EstadisticasJugador;

import java.util.List;

public interface EstadisticasJugadorPartidoService {

    public List<EstadisticasJugador> todasEstadisticas();

    public EstadisticasJugador obtenerEstadisticasPorId(Integer id);

   public List<EstadisticasJugador> obtenerEstadisticasPorIdJugador(Integer idJugador);

    public EstadisticasJugador obtenerEstadisticasPorIdJugadorIdPartido(Integer idJugador, Integer idPartido);

    public EstadisticasJugador crearEstadisticas(EstadisticasJugador estadisticas);

    public EstadisticasJugador actualizarEstadisticas(Integer id, EstadisticasJugador estadisticas);

    public void eliminarEstadisticas(Integer id);
}
