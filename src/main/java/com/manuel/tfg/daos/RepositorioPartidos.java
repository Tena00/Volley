package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPartidos extends JpaRepository<Partido,Integer> {
}
