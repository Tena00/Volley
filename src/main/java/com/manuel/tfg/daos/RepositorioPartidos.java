package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.daos.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioPartidos extends JpaRepository<Partido,Integer> {

    @Query("SELECT e FROM Partido e WHERE e.equipo.idEquipo = ?1")
    List<Partido> findByIdPartidoIdEquipo(Integer idEquipo);

}
