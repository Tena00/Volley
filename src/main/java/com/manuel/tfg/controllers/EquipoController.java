package com.manuel.tfg.controllers;

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

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private EquipoService equipoService;
    private JugadoresService jugadoresService;
    public EquipoController(EquipoService equipoService, JugadoresService jugadoresService) {
        this.equipoService = equipoService;
        this.jugadoresService = jugadoresService;
    }

    @GetMapping("")
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

    @DeleteMapping("/eliminarEquipo/{id}")
    public ResponseEntity eliminarEquipo(@PathVariable(value = "id") Integer equipoId){

        try {
            equipoService.eliminarEquipo(equipoId);
            return ResponseEntity.ok("Equipo borrado exitosamente.");
        }catch (EquipoExistenteExcepcion e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idEquipo}/jugadores")
    public List<Jugador> obtenerJugadoresPorEquipo(@PathVariable Integer idEquipo) {
        return equipoService.obtenerJugadoresPorEquipo(idEquipo);
    }
}
