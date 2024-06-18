package com.manuel.tfg.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.EquipoService;
import com.manuel.tfg.services.JugadoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://db5015951811.hosting-data.io")
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private EquipoService equipoService;
    private JugadoresService jugadoresService;
    public EquipoController(EquipoService equipoService, JugadoresService jugadoresService) {
        this.equipoService = equipoService;
        this.jugadoresService = jugadoresService;
    }

    @GetMapping("todos")
    public List<Equipo> todosEquipos(){
        return equipoService.todosEquipos();
    }

    @PostMapping("/addEquipo")
    public ResponseEntity addEquipo(@RequestBody Equipo equipo){
        try {

            equipoService.addEquipo(equipo);
            return ResponseEntity.ok("Equipo agregado exitosamente.");
        } catch (EquipoExistenteExcepcion e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/eliminarEquipo/{idEquipo}")
    public ResponseEntity eliminarEquipo(@PathVariable("idEquipo") Integer idEquipo) {
        try {
            equipoService.eliminarEquipo(idEquipo);
            return ResponseEntity.ok("Equipo borrado exitosamente.");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El ID del equipo debe ser un número entero.");
        } catch (EquipoExistenteExcepcion | JugadorExistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{idEquipo}/jugadores")
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable("idEquipo") Integer idEquipo) {
        if (idEquipo == null || idEquipo <= 0) {
            return ResponseEntity.badRequest().build(); // Devuelve un error 400 si idEquipo no es válido
        }

        List<Jugador> jugadores = equipoService.obtenerJugadoresPorEquipo(idEquipo);

        if (jugadores == null || jugadores.isEmpty()) {
            return ResponseEntity.notFound().build(); // Devuelve un error 404 si no se encontraron jugadores
        }

        return ResponseEntity.ok(jugadores);
    }
}
