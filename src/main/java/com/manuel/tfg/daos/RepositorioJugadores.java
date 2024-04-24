package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioJugadores extends JpaRepository<Jugador,Integer> {
    List<Jugador> findByEquipoIdEquipo(Integer idEquipo);
}
