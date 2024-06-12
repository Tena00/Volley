package com.manuel.tfg.controllers;

import com.manuel.tfg.services.AccionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accion")
public class AccionesController {

    private AccionesService accionesService;

    public AccionesController(AccionesService accionesService) {
        this.accionesService = accionesService;
    }

    @PostMapping("/remate")
    public ResponseEntity realizarAccion(@RequestBody Map<String, Object> body) {
        Integer idPartido = (Integer) body.get("idPartido");
        Integer idJugador = Integer.parseInt((String) body.get("idJugador"));
        Integer idZona = Integer.parseInt((String) body.get("idZona"));
        Integer idZonaAtaque = Integer.parseInt((String) body.get("idZonaAtaque"));
        String resultadoAccion = (String) body.get("resultadoAccion");

        accionesService.realizarAccion(idPartido, idJugador, idZona, idZonaAtaque, resultadoAccion);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se ha registrado bien la acción.");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/saque")
    public ResponseEntity realizarSaque(@RequestBody Map<String, Object> body) {
        Integer idPartido = (Integer) body.get("idPartido");
        Integer idJugador = Integer.parseInt((String) body.get("idJugador"));
        Integer idZona = Integer.parseInt((String) body.get("idZona"));
        Integer idZonaAtaque = Integer.parseInt((String) body.get("idZonaAtaque"));
        String resultadoAccion = (String) body.get("resultadoAccion");

        accionesService.realizarSaque(idPartido, idJugador, idZona, idZonaAtaque, resultadoAccion);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se ha registrado bien la acción.");

        return ResponseEntity.ok(response);
    }

}
