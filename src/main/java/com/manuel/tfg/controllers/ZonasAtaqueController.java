package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.ZonasAtaque;
import com.manuel.tfg.services.ZonaAtaqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonasAtaque")
public class ZonasAtaqueController {

    private ZonaAtaqueService zonasAtaqueService;

    public ZonasAtaqueController(ZonaAtaqueService zonasAtaqueService) {
        this.zonasAtaqueService = zonasAtaqueService;
    }

    @GetMapping("")
    public List<ZonasAtaque> mostrarZonas(){
        return zonasAtaqueService.mostrarZonas();
    }
    @GetMapping("/estadisticas")
    public List<EstadisticasAtaque> mostrarEstadisticasZona(){
        return zonasAtaqueService.mostrarEstadisticas();
    }
}
