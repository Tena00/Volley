package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Partido;

import java.util.List;

public interface PartidoService {

    public List<Partido> todosPartidos();

    public Integer iniciarPartido(Integer idEquipo);

    public List<EstadisticasJugador> estadisticasPartido(Integer idPartido);

    public void borrarPartido(Integer idPartido);
}
