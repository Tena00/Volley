package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioJugadores extends JpaRepository<Jugador,Integer> {
    List<Jugador> findByEquipoIdEquipo(Integer idEquipo);

    @Query("SELECT e FROM Jugador e WHERE e.titular= true")
    List<Jugador> findAllByTitular();

    @Query("SELECT e FROM Jugador e WHERE e.titular= false")
    List<Jugador> findAllBySuplentes();
}
