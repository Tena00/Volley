package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Acciones;
import com.manuel.tfg.daos.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioAcciones extends JpaRepository<Acciones,Integer> {

    @Query("SELECT e FROM Acciones e WHERE e.partido.idPartido = ?1")
    List<Acciones> findByIdAccionesPartido(Integer idPartido);
}
