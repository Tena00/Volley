package com.manuel.tfg.services;

import org.springframework.web.bind.annotation.RequestParam;

public interface AccionesService {

    public void realizarAccion(Integer idPartido, Integer idJugador, Integer idZona, Integer idZonaAtaque, String resultadoAccion);

    public void realizarSaque(Integer idPartido, Integer idJugador, Integer idZona, Integer idZonaAtaque, String resultadoAccion);

    public void eliminarAccion(Integer id);
}
