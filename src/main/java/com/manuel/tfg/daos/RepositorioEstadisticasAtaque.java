package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.EstadisticasAtaque;
import com.manuel.tfg.daos.model.EstadisticasZona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioEstadisticasAtaque extends JpaRepository<EstadisticasAtaque,Integer> {

    @Query("SELECT e FROM EstadisticasAtaque e WHERE e.partido.idPartido = ?1 AND e.idZonaAtaque.idZonaAtaque = ?2")
    EstadisticasAtaque findByIdPartidoIdZonaAtaque(Integer idPartido, Integer idZonaAtaque);

    @Query("SELECT e FROM EstadisticasAtaque e WHERE e.partido.idPartido = ?1")
    List<EstadisticasAtaque> findByIdPartido(Integer idPartido);

}
