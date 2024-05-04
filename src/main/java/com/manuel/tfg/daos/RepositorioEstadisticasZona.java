package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.daos.model.EstadisticasZona;
import com.manuel.tfg.daos.model.ZonasCampo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositorioEstadisticasZona extends JpaRepository<EstadisticasZona,Integer> {

    @Query("SELECT e FROM EstadisticasZona e WHERE e.partido.idPartido = ?1 AND e.idZona.idZonaCampo = ?2")
    EstadisticasZona findByIdPartidoIdZona(Integer idPartido, Integer idZona);
}
