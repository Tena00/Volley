package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.daos.model.Partido;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> iniciarPartido(@PathVariable("id") Integer idEquipo) {
        partidoService.iniciarPartido(idEquipo);
        return ResponseEntity.ok("{\"mensaje\": \"Partido iniciado.\"}");
    }

    @GetMapping("/estadisticas/{id}")
    public List<EstadisticasJugador> estadisticasPartido(@PathVariable("id") Integer idPartido){
       return  partidoService.estadisticasPartido(idPartido);
    }


}
