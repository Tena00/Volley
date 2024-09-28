package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioAcciones;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.RepositorioEstadisticasZona;
import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<EstadisticasZona> estadisticasZonas = repositorioEstadisticasZona.findByIdPartido(idPartido);

        Map<ZonasCampo, List<EstadisticasZona>> estadisticasPorIdZona = estadisticasZonas.stream()
                .collect(Collectors.groupingBy(EstadisticasZona::getIdZona));

        // Obtener solo las listas agrupadas por idZona y devolverlas
        List<List<EstadisticasZona>> listasAgrupadas = new ArrayList<>(estadisticasPorIdZona.values());

        return estadisticasZonasPartidoSuma(listasAgrupadas);
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

    @Override
    public int[] mostrarEstadisticasTotalesPartidoJugador(Integer idPartido, Integer idJugador) {
        List<EstadisticasZona> estadisticasZonaList = repositorioEstadisticasZona.findByEstadisticasJugadorPartido(idPartido,idJugador);
        int[] totalesPartidoJugador = new int[4];
        int rematesFallados = 0;
        int saquesFallados = 0;
        for (EstadisticasZona zona : estadisticasZonaList) {
            rematesFallados = zona.getRematesTotal() - (zona.getRematesPuntos() + zona.getRematesBloqueados());
            saquesFallados = zona.getSaquesTotal() - zona.getSaquesPuntos();
            totalesPartidoJugador[0] = totalesPartidoJugador[0] + (zona.getRematesTotal() + zona.getSaquesTotal());
            totalesPartidoJugador[1] = totalesPartidoJugador[1] + (zona.getRematesPuntos() + zona.getSaquesPuntos());
            totalesPartidoJugador[2] = totalesPartidoJugador[2] + zona.getRematesBloqueados();
            totalesPartidoJugador[3] = totalesPartidoJugador[3] + (rematesFallados + saquesFallados);
        }
        return totalesPartidoJugador;
    }

    private List<EstadisticasZona> estadisticasZonasPartidoSuma(List<List<EstadisticasZona>> listasAgrupadas){
        List<EstadisticasZona> listaEstadisticasZona = new ArrayList<>();

        for (List<EstadisticasZona> sublista : listasAgrupadas) {
            // Aquí puedes manipular cada sublista
            EstadisticasZona estadisticasZonaAux = new EstadisticasZona();
            iniciarEstadisticasZonaAux(estadisticasZonaAux);
            for (EstadisticasZona estadistica : sublista) {
                estadisticasZonaAux.setIdZona(estadistica.getIdZona());
                estadisticasZonaAux.setRematesTotal(estadisticasZonaAux.getRematesTotal() + estadistica.getRematesTotal());
                estadisticasZonaAux.setSaquesTotal(estadisticasZonaAux.getSaquesTotal() + estadistica.getSaquesTotal());
                estadisticasZonaAux.setRematesBloqueados(estadisticasZonaAux.getRematesBloqueados() + estadistica.getRematesBloqueados());
                estadisticasZonaAux.setRematesPuntos(estadisticasZonaAux.getRematesPuntos() + estadistica.getRematesPuntos());
                estadisticasZonaAux.setSaquesPuntos(estadisticasZonaAux.getSaquesPuntos() + estadistica.getSaquesPuntos());

            }
            listaEstadisticasZona.add(estadisticasZonaAux);
        }
        return listaEstadisticasZona;
    }

    private void iniciarEstadisticasZonaAux(EstadisticasZona estadisticasZona){
        estadisticasZona.setRematesTotal(0);
        estadisticasZona.setSaquesTotal(0);
        estadisticasZona.setRematesBloqueados(0);
        estadisticasZona.setRematesPuntos(0);
        estadisticasZona.setSaquesPuntos(0);
    }
}
