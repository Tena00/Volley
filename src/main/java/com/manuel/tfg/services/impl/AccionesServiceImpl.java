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
    private RepositorioZonasAtaque repositorioZonasAtaque;
    private RepositorioEstadisticasAtaque repositorioEstadisticasAtaque;


    public AccionesServiceImpl(RepositorioEstadisticasAtaque repositorioEstadisticasAtaque, RepositorioEstadisticasZona repositorioEstadisticasZona, RepositorioEstadisticas repositorioEstadisticas, RepositorioAcciones repositorioAcciones, RepositorioJugadores repositorioJugadores, RepositorioPartidos repositorioPartidos, RepositorioZonas repositorioZonas, RepositorioZonasAtaque repositorioZonasAtaque) {
        this.repositorioEstadisticasZona = repositorioEstadisticasZona;
        this.repositorioEstadisticas = repositorioEstadisticas;
        this.repositorioAcciones = repositorioAcciones;
        this.repositorioJugadores = repositorioJugadores;
        this.repositorioPartidos = repositorioPartidos;
        this.repositorioZonas = repositorioZonas;
        this.repositorioZonasAtaque = repositorioZonasAtaque;
        this.repositorioEstadisticasAtaque = repositorioEstadisticasAtaque;
    }

    public void realizarAccion(Integer idPartido, Integer idJugador, Integer idZona, Integer idZonaAtaque, String resultadoAccion) {
        EstadisticasJugador estadisticasJugador = repositorioEstadisticas.findByIdPartidoIdJugador(idPartido, idJugador);
        EstadisticasZona estadisticasZona = repositorioEstadisticasZona.findByIdPartidoIdZona(idPartido, idZona);
        EstadisticasAtaque estadisticasAtaque = repositorioEstadisticasAtaque.findByIdPartidoIdZonaAtaque(idPartido, idZonaAtaque);
        Optional<Jugador> jugadorOp = repositorioJugadores.findById(idJugador);
        Jugador jugador = jugadorOp.get();
        Optional<Partido> partidoOp = repositorioPartidos.findById(idPartido);
        Optional<ZonasCampo> zonasCampoOp = repositorioZonas.findById(idZona);
        Optional<ZonasAtaque> zonasAtaqueOp = repositorioZonasAtaque.findById(idZonaAtaque);
        Acciones accionesPartido = new Acciones();
        accionesPartido.setEstadisticasZona(estadisticasZona);
        accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
        if (ObjectUtils.isEmpty(estadisticasJugador)) {
            estadisticasJugador = new EstadisticasJugador();
            inicializarEstadisticas(estadisticasJugador);
            Partido partido = partidoOp.get();
            estadisticasJugador.setJugador(jugador);
            estadisticasJugador.setPartido(partido);
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona) && ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasAtaque = new EstadisticasAtaque();
                estadisticasZona.setJugador(jugador);
                inicializarEstadisticasZona(estadisticasZona);
                inicializarEstadisticasAtaque(estadisticasAtaque);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10|| idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasAtaque(estadisticasAtaque);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasZona)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasZona.setJugador(jugador);
                inicializarEstadisticasZona(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            } else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            }
        } else {
            Partido partido = partidoOp.get();
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona) && ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasAtaque = new EstadisticasAtaque();
                estadisticasZona.setJugador(jugador);
                inicializarEstadisticasZona(estadisticasZona);
                inicializarEstadisticasAtaque(estadisticasAtaque);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasAtaque(estadisticasAtaque);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasZona)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasZona.setJugador(jugador);
                inicializarEstadisticasZona(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            } else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    remateFallado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    remateBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    rematePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            }
        }
        accionesPartido.setColoResultado(resultadoAccion);
        repositorioEstadisticasZona.save(estadisticasZona);
        repositorioEstadisticas.save(estadisticasJugador);
        repositorioEstadisticasAtaque.save(estadisticasAtaque);
        repositorioAcciones.save(accionesPartido);

    }


    public void eliminarAccion(Integer id) {
        repositorioAcciones.deleteById(id);
    }

    private void inicializarEstadisticas(EstadisticasJugador estadisticasJugador) {
        estadisticasJugador.setRematesTotal(0);
        estadisticasJugador.setRematesBloqueados(0);
        estadisticasJugador.setRematesPuntos(0);
    }

    private void inicializarEstadisticasZona(EstadisticasZona estadisticasZona) {
        estadisticasZona.setRematesTotal(0);
        estadisticasZona.setRematesBloqueados(0);
        estadisticasZona.setRematesPuntos(0);
    }

    private void remateFallado(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, EstadisticasAtaque estadisticasAtaque) {
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal() + 1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal() + 1);
        estadisticasAtaque.setRematesTotal(estadisticasAtaque.getRematesTotal()+1);
    }

    private void remateBloqueado(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, EstadisticasAtaque estadisticasAtaque) {
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal() + 1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal() + 1);
        estadisticasAtaque.setRematesTotal(estadisticasAtaque.getRematesTotal() + 1);
        estadisticasJugador.setRematesBloqueados(estadisticasJugador.getRematesBloqueados() + 1);
        estadisticasZona.setRematesBloqueados(estadisticasZona.getRematesBloqueados() + 1);
        estadisticasAtaque.setRematesBloqueados(estadisticasAtaque.getRematesBloqueados() + 1);
    }

    private void rematePunto(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, EstadisticasAtaque estadisticasAtaque) {
        estadisticasJugador.setRematesTotal(estadisticasJugador.getRematesTotal() + 1);
        estadisticasZona.setRematesTotal(estadisticasZona.getRematesTotal() + 1);
        estadisticasAtaque.setRematesTotal(estadisticasAtaque.getRematesTotal() + 1);
        estadisticasJugador.setRematesPuntos(estadisticasJugador.getRematesPuntos() + 1);
        estadisticasZona.setRematesPuntos(estadisticasZona.getRematesPuntos() + 1);
        estadisticasAtaque.setRematesPuntos(estadisticasAtaque.getRematesPuntos() + 1);
    }


    private void inicializarEstadisticasAtaque(EstadisticasAtaque estadisticasAtaque) {
        estadisticasAtaque.setRematesTotal(0);
        estadisticasAtaque.setRematesBloqueados(0);
        estadisticasAtaque.setRematesPuntos(0);
    }

    public void realizarSaque(Integer idPartido, Integer idJugador, Integer idZona, Integer idZonaAtaque, String resultadoAccion){
        EstadisticasJugador estadisticasJugador = repositorioEstadisticas.findByIdPartidoIdJugador(idPartido, idJugador);
        EstadisticasZona estadisticasZona = repositorioEstadisticasZona.findByIdPartidoIdZona(idPartido, idZona);
        EstadisticasAtaque estadisticasAtaque = repositorioEstadisticasAtaque.findByIdPartidoIdZonaAtaque(idPartido, idZonaAtaque);
        Optional<Jugador> jugadorOp = repositorioJugadores.findById(idJugador);
        Optional<Partido> partidoOp = repositorioPartidos.findById(idPartido);
        Optional<ZonasCampo> zonasCampoOp = repositorioZonas.findById(idZona);
        Optional<ZonasAtaque> zonasAtaqueOp = repositorioZonasAtaque.findById(idZonaAtaque);
        Acciones accionesPartido = new Acciones();
        accionesPartido.setEstadisticasZona(estadisticasZona);
        accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
        if (ObjectUtils.isEmpty(estadisticasJugador)) {
            estadisticasJugador = new EstadisticasJugador();
            inicializarEstadisticasSaque(estadisticasJugador);
            Partido partido = partidoOp.get();
            Jugador jugador = jugadorOp.get();
            estadisticasJugador.setJugador(jugador);
            estadisticasJugador.setPartido(partido);
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona) && ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasZonaSaque(estadisticasZona);
                inicializarEstadisticasAtaqueSaque(estadisticasAtaque);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasAtaqueSaque(estadisticasAtaque);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasZona)) {
                estadisticasZona = new EstadisticasZona();
                inicializarEstadisticasZonaSaque(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            } else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            }
        } else {
            Partido partido = partidoOp.get();
            accionesPartido.setEstadisticasJugador(estadisticasJugador);
            accionesPartido.setPartido(partido);
            if (ObjectUtils.isEmpty(estadisticasZona) && ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasZona = new EstadisticasZona();
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasZonaSaque(estadisticasZona);
                inicializarEstadisticasAtaqueSaque(estadisticasAtaque);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasAtaque)) {
                estadisticasAtaque = new EstadisticasAtaque();
                inicializarEstadisticasAtaqueSaque(estadisticasAtaque);
                ZonasAtaque zonasAtaque = zonasAtaqueOp.get();
                estadisticasAtaque.setPartido(partido);
                estadisticasAtaque.setIdZonaAtaque(zonasAtaque);
                accionesPartido.setEstadisticasAtaque(estadisticasAtaque);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }

            } else if (ObjectUtils.isEmpty(estadisticasZona)) {
                estadisticasZona = new EstadisticasZona();
                inicializarEstadisticasZonaSaque(estadisticasZona);
                ZonasCampo zonasCampo = zonasCampoOp.get();
                estadisticasZona.setPartido(partido);
                estadisticasZona.setIdZona(zonasCampo);
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            } else {
                accionesPartido.setEstadisticasZona(estadisticasZona);
                if ((resultadoAccion.equals("rojo")) || idZonaAtaque == 10 || idZonaAtaque == 11 || idZonaAtaque == 12 ) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("amarillo")) {
                    saqueBloqueado(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                } else if (resultadoAccion.equals("verde")) {
                    saquePunto(estadisticasJugador, estadisticasZona, estadisticasAtaque);
                }
            }
        }
        accionesPartido.setColoResultado(resultadoAccion);
        repositorioEstadisticasZona.save(estadisticasZona);
        repositorioEstadisticas.save(estadisticasJugador);
        repositorioEstadisticasAtaque.save(estadisticasAtaque);
        repositorioAcciones.save(accionesPartido);
    }

    private void inicializarEstadisticasSaque(EstadisticasJugador estadisticasJugador) {
        estadisticasJugador.setSaquesTotal(0);
        estadisticasJugador.setSaquesPuntos(0);
    }

    private void inicializarEstadisticasZonaSaque(EstadisticasZona estadisticasZona) {
        estadisticasZona.setSaquesTotal(0);
        estadisticasZona.setSaquesPuntos(0);
    }

    private void inicializarEstadisticasAtaqueSaque(EstadisticasAtaque estadisticasAtaque) {
        estadisticasAtaque.setSaquesTotal(0);
        estadisticasAtaque.setSaquesPuntos(0);
    }

    private void saqueBloqueado(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, EstadisticasAtaque estadisticasAtaque) {
        estadisticasJugador.setSaquesTotal(estadisticasJugador.getSaquesTotal() + 1);
        estadisticasZona.setSaquesTotal(estadisticasZona.getSaquesTotal() + 1);
        estadisticasAtaque.setSaquesTotal(estadisticasAtaque.getSaquesTotal() + 1);

    }

    private void saquePunto(EstadisticasJugador estadisticasJugador, EstadisticasZona estadisticasZona, EstadisticasAtaque estadisticasAtaque) {
        estadisticasJugador.setSaquesTotal(estadisticasJugador.getSaquesTotal() + 1);
        estadisticasZona.setSaquesTotal(estadisticasZona.getSaquesTotal() + 1);
        estadisticasAtaque.setSaquesTotal(estadisticasAtaque.getSaquesTotal() + 1);
        estadisticasJugador.setSaquesPuntos(estadisticasJugador.getSaquesPuntos() + 1);
        estadisticasZona.setSaquesPuntos(estadisticasZona.getSaquesPuntos() + 1);
        estadisticasAtaque.setSaquesPuntos(estadisticasAtaque.getSaquesPuntos() + 1);
    }
}
