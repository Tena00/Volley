package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioZonas;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonasCampoServiceImpl implements ZonasCampoService {

    private RepositorioZonas repositorioZonas;

    public ZonasCampoServiceImpl(RepositorioZonas repositorioZonas) {
        this.repositorioZonas = repositorioZonas;
    }

    @Override
    public List<ZonasCampo> mostrarZonas() {
        return repositorioZonas.findAll();
    }
}
