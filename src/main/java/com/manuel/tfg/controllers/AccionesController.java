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
    public ResponseEntity<Map<String, String>> realizarRemate(@RequestBody Map<String, Object> body) {
        try {
            Integer idPartido = (Integer) body.get("idPartido");
            Integer idJugador = (Integer) body.get("idJugador");
            Integer idZona = (Integer) body.get("idZona");
            Integer idZonaAtaque = (Integer) body.get("idZonaAtaque");
            String resultadoAccion = (String) body.get("resultadoAccion");

            accionesService.realizarAccion(idPartido, idJugador, idZona, idZonaAtaque, resultadoAccion);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Se ha registrado bien la acción.");
            return ResponseEntity.ok(response);
        } catch (NumberFormatException | ClassCastException e) {
            // Manejar el error de conversión de tipos
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al convertir o castear tipo de datos.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/saque")
    public ResponseEntity<Map<String, String>> realizarSaque(@RequestBody Map<String, Object> body) {
        try {
            Integer idPartido = (Integer) body.get("idPartido");
            Integer idJugador = (Integer) body.get("idJugador");
            Integer idZona = (Integer) body.get("idZona");
            Integer idZonaAtaque = (Integer) body.get("idZonaAtaque");
            String resultadoAccion = (String) body.get("resultadoAccion");

            accionesService.realizarSaque(idPartido, idJugador, idZona, idZonaAtaque, resultadoAccion);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Se ha registrado bien la acción.");
            return ResponseEntity.ok(response);
        } catch (NumberFormatException | ClassCastException e) {
            // Manejar el error de conversión de tipos
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al convertir o castear tipo de datos.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


}

