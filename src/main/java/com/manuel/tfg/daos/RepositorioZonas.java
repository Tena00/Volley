package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.ZonasCampo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioZonas extends JpaRepository<ZonasCampo,Integer> {

    @Query("SELECT e FROM EstadisticasJugador e WHERE e.jugador.idJugador = ?1")
    List<EstadisticasJugador> findByIdJugador(Integer idJugador);
}
