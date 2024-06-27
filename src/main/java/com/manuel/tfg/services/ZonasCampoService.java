package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ZonasCampoService {

    public List<ZonasCampo> mostrarZonas();

    public List<EstadisticasZona> mostrarEstadisticas();

    public List<EstadisticasZona> mostrarEstadisticasPartido(Integer idPartido);

    public List<EstadisticasZona> mostrarEstadisticasPartidoJugadores(Integer idPartido, Integer idJugador);

    public int[] mostrarEstadisticasTotalesPartido(Integer idPartido);

    public void eliminarEstadisticasCampo(Integer idEstadisticasZona);

//    public List<EstadisticasZona> mostrarEstadisticasJugadorPartido(Integer idPartido);
//
//    public List<EstadisticasZona> mostrarEstadisticasZonaPartidoJugador( Integer idPartido, Integer idJugador, Integer idZona);
}
