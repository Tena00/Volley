package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.*;
import com.manuel.tfg.daos.model.*;
import com.manuel.tfg.services.AccionesService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class AccionesServiceImpl implements AccionesService {

    private RepositorioEstadisticasZona repositorioEstadisticasZona;
    private RepositorioEstadisticas repositorioEstadisticas;
    private RepositorioAcciones repositorioAcciones;
    private RepositorioJugadores repositorioJugadores;
    private RepositorioPartidos repositorioPartidos;
    private RepositorioZonas repositorioZonas;


    public AccionesServiceImpl(RepositorioEstadisticasZona repositorioEstadisticasZona, RepositorioEstadisticas repositorioEstadisticas, RepositorioAcciones repositorioAcciones, RepositorioJugadores repositorioJugadores, RepositorioPartidos repositorioPartidos, RepositorioZonas repositorioZonas) {
        this.repositorioEstadisticasZona = repositorioEstadisticasZona;
        this.repositorioEstadisticas = repositorioEstadisticas;
        this.repositorioAcciones = repositorioAcciones;
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioPartidos = repositorioPartidos;
        this.repositorioZonas = repositorioZonas;
    }

    public void realizarAccion(Integer idPartido, Integer idJugador, Integer idZona, String resultadoAccion){
        EstadisticasJugador estadisticasJugador = repositorioEstadisticas.findByIdPartidoIdJugador(idPartido,idJugador);
        EstadisticasZona estadisticasZona = repositorioEstadisticasZona.findByIdPartidoIdZona(idPartido,idZona);
        Optional<Jugador> jugadorOp = repositorioJugadores.findById(idJugador);
        Optional<Partido> partidoOp = repositorioPartidos.findById(idPartido);
        Optional<ZonasCampo> zonasCampoOp = repositorioZonas.findById(idZona);
        Acciones accionesPartido = new Acciones();
        if (ObjectUtils.isEmpty(estadisticasJugador)){
            estadisticasJugador = new EstadisticasJugador();
            inicializarEstadisticas(estadisticasJugador);
            Partido partido = partidoOp.get();
            Jugador jugador = jugadorOp.get();
            estadisticasJugador.setJugador(jugador);
            estadisticasJugador.setPartido(partido);
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona)){
                estadisticasZona = new EstadisticasZona();
                inicializarEstadisticasZona(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("Rojo"))){
                    remateFallado(estadisticasJugador,estadisticasZona);
                }else if (resultadoAccion.equals("Amarillo")){
                    remateBloqueado(estadisticasJugador,estadisticasZona);
                } else if (resultadoAccion.equals("Verde")) {
                    rematePunto(estadisticasJugador,estadisticasZona);
                }

            }else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("Rojo"))){
                    remateFallado(estadisticasJugador,estadisticasZona);
                }else if (resultadoAccion.equals("Amarillo")){
                    remateBloqueado(estadisticasJugador,estadisticasZona);
                } else if (resultadoAccion.equals("Verde")) {
                    rematePunto(estadisticasJugador,estadisticasZona);
                }
            }
        }else {
            Partido partido = partidoOp.get();
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona)){
                estadisticasZona = new EstadisticasZona();
                inicializarEstadisticasZona(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("Rojo"))){
                    remateFallado(estadisticasJugador,estadisticasZona);
                }else if (resultadoAccion.equals("Amarillo")){
                    remateBloqueado(estadisticasJugador,estadisticasZona);
                } else if (resultadoAccion.equals("Verde")) {
                    rematePunto(estadisticasJugador,estadisticasZona);
                }

            }else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("Rojo"))) {
                    remateFallado(estadisticasJugador, estadisticasZona);
                } else if (resultadoAccion.equals("Amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona);
                } else if (resultadoAccion.equals("Verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona);
                }
            }
        }
        accionesPartido.setColoResultado(resultadoAccion);
        repositorioEstadisticasZona.save(estadisticasZona);
        repositorioEstadisticas.save(estadisticasJugador);
        repositorioAcciones.save(accionesPartido);

    }

    public void eliminarAccion(Integer id){
        repositorioAcciones.deleteById(id);
    }

    private void inicializarEstadisticas(EstadisticasJugador estadisticasJugador){
        estadisticasJugador.setRematesTotal(0);
        estadisticasJugador.setRematesBloqueados(0);
        estadisticasJugador.setRematesPuntos(0);
        estadisticasJugador.setSaquesTotal(0);
        estadisticasJugador.setSaquesPuntos(0);
    }
    private void inicializarEstadisticasZona(EstadisticasZona estadisticasZona){
        estadisticasZona.setRematesTotal(0);
        estadisticasZona.setRematesBloqueados(0);
        estadisticasZona.setRematesPuntos(0);
        estadisticasZona.setSaquesTotal(0);
        estadisticasZona.setSaquesPuntos(0);
    }

    private void remateFallado(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona){
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal()+1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal()+1);
    }

    private void remateBloqueado(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona){
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal()+1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal()+1);
        estadisticasJugador.setRematesBloqueados(estadisticasJugador.getRematesBloqueados()+1);
        estadisticasZona.setRematesBloqueados(estadisticasZona.getRematesBloqueados()+1);
    }
    private void rematePunto(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona){
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal()+1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal()+1);
        estadisticasJugador.setRematesPuntos(estadisticasJugador.getRematesPuntos()+1);
        estadisticasZona.setRematesPuntos(estadisticasZona.getRematesPuntos()+1);
    }

}
