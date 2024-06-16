package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.services.EstadisticasJugadorPartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {


    private EstadisticasJugadorPartidoService estadisticasService;

    public EstadisticasController(EstadisticasJugadorPartidoService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("")
    public List<EstadisticasJugador> obtenerEstadisticas() {
        return estadisticasService.todasEstadisticas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> obtenerEstadisticasPorId(@PathVariable Integer id) {
        EstadisticasJugador estadisticas = estadisticasService.obtenerEstadisticasPorId(id);
        return ResponseEntity.ok().body(estadisticas);
    }

   @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<EstadisticasJugador>> obtenerEstadisticasPorIdJugador(@PathVariable Integer idJugador) {
        List<EstadisticasJugador> estadisticas = estadisticasService.obtenerEstadisticasPorIdJugador(idJugador);
        return ResponseEntity.ok().body(estadisticas);
    }

    @GetMapping("{idPartido}/{idJugador}")
    public ResponseEntity<EstadisticasJugador> obtenerEstadisticasPorIdJugadorIdPartido(@PathVariable Integer idJugador,@PathVariable Integer idPartido ) {
        EstadisticasJugador estadisticas = estadisticasService.obtenerEstadisticasPorIdJugadorIdPartido(idJugador,idPartido);
        return ResponseEntity.ok().body(estadisticas);
    }

    @PostMapping("/")
    public ResponseEntity<EstadisticasJugador> crearEstadisticas(@RequestBody EstadisticasJugador estadisticas) {
        EstadisticasJugador nuevaEstadisticas = estadisticasService.crearEstadisticas(estadisticas);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEstadisticas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadisticasJugador> actualizarEstadisticas(@PathVariable Integer id, @RequestBody EstadisticasJugador estadisticas) {
        EstadisticasJugador estadisticasActualizadas = estadisticasService.actualizarEstadisticas(id, estadisticas);
        return ResponseEntity.ok().body(estadisticasActualizadas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadisticas(@PathVariable Integer id) {
        estadisticasService.eliminarEstadisticas(id);
        return ResponseEntity.noContent().build();
    }
}
