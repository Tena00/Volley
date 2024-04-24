package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEquipos extends JpaRepository<Equipo,Integer> {
}
