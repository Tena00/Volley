package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioEquipos extends JpaRepository<Equipo,Integer> {

    @Query("SELECT e FROM Jugador e WHERE e.equipo.idEquipo = ?1")
    List<Jugador> findByIdEquipoIdJugador(Integer idEquipo);
}
