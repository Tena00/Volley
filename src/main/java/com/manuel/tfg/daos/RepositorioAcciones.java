package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Acciones;
import com.manuel.tfg.daos.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAcciones extends JpaRepository<Acciones,Integer> {
}
