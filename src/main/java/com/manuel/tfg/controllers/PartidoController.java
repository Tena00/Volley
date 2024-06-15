package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.daos.model.Partido;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    private PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping("")
    public List<Partido> mostrarTodosPartidos(){
        return partidoService.todosPartidos();
    }

    @PostMapping("/iniciarPartido/{id}")
    public ResponseEntity<Map<String, Integer>> iniciarPartido(@PathVariable("id") Integer idEquipo) {
        Integer idPartido = partidoService.iniciarPartido(idEquipo);

        // Crear un mapa para almacenar los IDs y devolverlo como JSON
        Map<String, Integer> response = new HashMap<>();
        response.put("idEquipo", idEquipo);
        response.put("idPartido", idPartido);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/estadisticas/{id}")
    public List<EstadisticasJugador> estadisticasPartido(@PathVariable("id") Integer idPartido){
       return  partidoService.estadisticasPartido(idPartido);
    }


}
