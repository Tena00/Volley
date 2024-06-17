package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioAcciones;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.RepositorioEstadisticasZona;
import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.model.Acciones;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZonasCampoServiceImpl implements ZonasCampoService {

    private RepositorioZonas repositorioZonas;
    private RepositorioEstadisticasZona repositorioEstadisticasZona;
    private RepositorioAcciones repositorioAcciones;
    private RepositorioEstadisticas repositorioEstadisticas;

    public ZonasCampoServiceImpl(RepositorioZonas repositorioZonas, RepositorioEstadisticasZona repositorioEstadisticasZona, RepositorioAcciones repositorioAcciones, RepositorioEstadisticas repositorioEstadisticas ) {
        this.repositorioZonas = repositorioZonas;
        this.repositorioEstadisticasZona = repositorioEstadisticasZona;
        this.repositorioAcciones = repositorioAcciones;
        this.repositorioEstadisticas = repositorioEstadisticas;
    }

    @Override
    public List<ZonasCampo> mostrarZonas() {
        return repositorioZonas.findAll();
    }

    @Override
    public List<EstadisticasZona> mostrarEstadisticas() {
        return repositorioEstadisticasZona.findAll();

    }
    @Override
    public List<EstadisticasZona> mostrarEstadisticasPartido(Integer idPartido) {
        return repositorioEstadisticasZona.findByIdPartido(idPartido);
    }

//    public List<EstadisticasZona> mostrarEstadisticasZonaPartidoJugador(Integer idPartido, Integer idJugador, Integer idZona){
//        EstadisticasZona estadisticasZonas = repositorioEstadisticasZona.findByIdPartidoIdZona(idPartido,idZona);
//        List<Acciones> acciones = repositorioAcciones.findByIdAccionesPartido(idPartido);
//        EstadisticasJugador estadisticasJugadors = repositorioEstadisticas.findByIdPartidoIdJugador(idPartido,idJugador);
//        List<EstadisticasZona> estadisticasZonasFinal = new ArrayList<>();
//        for(Acciones accion : acciones){
//
//        }
//    }
}
