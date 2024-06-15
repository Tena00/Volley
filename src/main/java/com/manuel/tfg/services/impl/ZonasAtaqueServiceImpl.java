package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEstadisticasAtaque;
import com.manuel.tfg.daos.RepositorioEstadisticasZona;
import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.RepositorioZonasAtaque;
import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasAtaque;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonaAtaqueService;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonasAtaqueServiceImpl implements ZonaAtaqueService {

    private RepositorioZonasAtaque repositorioZonasAtaque;
    private RepositorioEstadisticasAtaque repositorioEstadisticasAtaque;

    public ZonasAtaqueServiceImpl(RepositorioZonasAtaque repositorioZonasAtaque, RepositorioEstadisticasAtaque repositorioEstadisticasAtaque) {
        this.repositorioZonasAtaque = repositorioZonasAtaque;
        this.repositorioEstadisticasAtaque = repositorioEstadisticasAtaque;
    }

    @Override
    public List<ZonasAtaque> mostrarZonas() {
        return repositorioZonasAtaque.findAll();
    }

    @Override
    public List<EstadisticasAtaque> mostrarEstadisticas() {
        return repositorioEstadisticasAtaque.findAll();

    }
}
