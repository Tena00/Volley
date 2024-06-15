package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Acciones;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.EstadisticasZona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioEstadisticas extends JpaRepository<EstadisticasJugador,Integer> {

    @Query("SELECT e FROM EstadisticasJugador e WHERE e.jugador.idJugador = ?1")
    List<EstadisticasJugador> findByIdJugador(Integer idJugador);

    @Query("SELECT e FROM EstadisticasJugador e WHERE e.partido.idPartido = ?1")
    List<EstadisticasJugador> findByIdPartido(Integer idPartido);

    @Query("SELECT e FROM EstadisticasJugador e WHERE e.partido.idPartido = ?1 AND e.jugador.idJugador = ?2")
    EstadisticasJugador findByIdPartidoIdJugador(Integer idPartido, Integer idJugador);

    @Query("SELECT e FROM Acciones e WHERE e.estadisticasJugador.idEstadisticasJugadorPartido = ?1")
    List<Acciones> findByIdAccionesEstadisticas(Integer idEstadisticas);

}
