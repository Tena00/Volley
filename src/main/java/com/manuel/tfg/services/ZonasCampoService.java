package com.manuel.tfg.services;

import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;

import java.util.List;

public interface ZonasCampoService {

    public List<ZonasCampo> mostrarZonas();

    public List<EstadisticasZona> mostrarEstadisticas();
}
