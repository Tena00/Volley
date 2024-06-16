package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasAtaque;
import com.manuel.tfg.daos.model.ZonasCampo;

import java.util.List;

public interface ZonaAtaqueService {

    public List<ZonasAtaque> mostrarZonas();

    public List<EstadisticasAtaque> mostrarEstadisticas();

    public List<EstadisticasAtaque> mostrarEstadisticasPartido(Integer idPartido);
}
