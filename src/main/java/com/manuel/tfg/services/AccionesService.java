package com.manuel.tfg.services;

import org.springframework.web.bind.annotation.RequestParam;

public interface AccionesService {

    public void realizarAccion(Integer idPartido, Integer idJugador, Integer idZona, String resultadoAccion);
}
