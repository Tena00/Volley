package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEstadisticasZona;
import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonasCampoServiceImpl implements ZonasCampoService {

    private RepositorioZonas repositorioZonas;
    private RepositorioEstadisticasZona repositorioEstadisticasZona;

    public ZonasCampoServiceImpl(RepositorioZonas repositorioZonas, RepositorioEstadisticasZona repositorioEstadisticasZona) {
        this.repositorioZonas = repositorioZonas;
        this.repositorioEstadisticasZona = repositorioEstadisticasZona;
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
}
