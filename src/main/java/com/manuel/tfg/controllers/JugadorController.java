package com.manuel.tfg.controllers;

import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.JugadoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    public JugadoresService jugadoresService;

    public JugadorController(JugadoresService jugadoresService) {
        this.jugadoresService = jugadoresService;
    }

    @GetMapping("")
    public List<Jugador> mostrarTodosJugadores(){
        return jugadoresService.todosJugadores();
    }

    @PostMapping("/addJugador")
    public ResponseEntity addJugador(@RequestBody Jugador jugador){

        try {

            jugadoresService.addJugador(jugador);
            return ResponseEntity.ok("Jugador agregado exitosamente.");
        } catch (JugadorExistenteException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/eliminarJugador/{id}")
    public ResponseEntity eliminarJugador(@PathVariable(value = "id") Integer jugadorId){

        try {
            jugadoresService.eliminarJugador(jugadorId);
            return ResponseEntity.ok("Jugador borrado exitosamente.");
        }catch (JugadorExistenteException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/actualizarEquipo/{idJugador}/{idEquipo}")
    public ResponseEntity actualizarEquipo(@PathVariable("idJugador") Integer idJugador, @PathVariable("idEquipo") Integer idEquipo) {
        try {
            jugadoresService.actualizarEquipo(idJugador, idEquipo);
            return ResponseEntity.ok("Equipo del jugador actualizado exitosamente.");
        } catch (JugadorExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EquipoExistenteExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
