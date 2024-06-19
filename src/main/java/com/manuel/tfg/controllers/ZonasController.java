package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import com.manuel.tfg.services.ZonasCampoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/estadisticas")
    public List<EstadisticasZona> mostrarEstadisticasZona(){
        return zonasCampoService.mostrarEstadisticas();
    }

    @GetMapping("/estadisticas/{idPartido}")
    public List<EstadisticasZona> mostrarEstadisticasZonaPartido(@PathVariable Integer idPartido){
        return zonasCampoService.mostrarEstadisticasPartido(idPartido);
    }

    @GetMapping("/estadisticasJugador/{idJugador}")
    public List<EstadisticasZona> mostrarEstadisticasZonaPartido(
            @PathVariable Integer idJugador,
            @RequestParam Integer idPartido) {
        return zonasCampoService.mostrarEstadisticasPartidoJugadores(idPartido, idJugador);
    }
    @GetMapping("/estadisticasTotal/{idPartido}")
    public ResponseEntity<int[]> obtenerEstadisticasPartido(@PathVariable("idPartido") Integer idPartido) {

        int[] estadisticasZonaList = zonasCampoService.mostrarEstadisticasTotalesPartido(idPartido);

        return ResponseEntity.ok(estadisticasZonaList);
    }
//    @GetMapping("/estadisticas/jugador/{idPartido}/{idJugador}/{idZona}")
//    public List<EstadisticasZona> mostrarEstadisticasZonaPartidoJugador(@PathVariable Integer idPartido,@PathVariable Integer idJugador,@PathVariable Integer idZona){
//        return zonasCampoService.mostrarEstadisticasJugadorPartido(idPartido,idJugador,idZona);
//    }
}
