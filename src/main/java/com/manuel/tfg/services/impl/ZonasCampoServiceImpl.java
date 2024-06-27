package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioAcciones;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.RepositorioEstadisticasZona;
import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public List<EstadisticasZona> mostrarEstadisticasPartidoJugadores(Integer idPartido, Integer idJugador) {
        return repositorioEstadisticasZona.findByEstadisticasJugadorPartido(idPartido, idJugador);
    }


    @Override
    public int[] mostrarEstadisticasTotalesPartido(Integer idPartido) {
        List<EstadisticasZona> estadisticasZonaList = repositorioEstadisticasZona.findByIdPartido(idPartido);
        int[] totalesPartido = new int[4];
        int rematesFallados = 0;
        int saquesFallados = 0;
        for (EstadisticasZona zona : estadisticasZonaList) {
            rematesFallados = zona.getRematesTotal() - (zona.getRematesPuntos() + zona.getRematesBloqueados());
            saquesFallados = zona.getSaquesTotal() - zona.getSaquesPuntos();
            totalesPartido[0] = totalesPartido[0] + (zona.getRematesTotal() + zona.getSaquesTotal());
            totalesPartido[1] = totalesPartido[1] + (zona.getRematesPuntos() + zona.getSaquesPuntos());
            totalesPartido[2] = totalesPartido[2] + zona.getRematesBloqueados();
            totalesPartido[3] = totalesPartido[3] + (rematesFallados + saquesFallados);
        }
        return totalesPartido;
    }

    public void eliminarEstadisticasCampo(Integer idEstadisticasZona){
        Optional<EstadisticasZona> estadisticasZona = repositorioEstadisticasZona.findById(idEstadisticasZona);
        if (estadisticasZona.isPresent()){
            EstadisticasZona estadisticasZona1 = estadisticasZona.get();
            repositorioEstadisticasZona.delete(estadisticasZona1);
        }
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
