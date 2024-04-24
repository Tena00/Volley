package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonas")
public class ZonasController {

    private ZonasCampoService zonasCampoService;

    public ZonasController(ZonasCampoService zonasCampoService) {
        this.zonasCampoService = zonasCampoService;
    }

    @GetMapping("")
    public List<ZonasCampo> mostrarZonas(){
        return zonasCampoService.mostrarZonas();
    }
}
