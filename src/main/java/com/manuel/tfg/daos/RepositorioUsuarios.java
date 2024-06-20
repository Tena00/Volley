package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer> {

    @Query("SELECT e FROM Usuario e WHERE e.nombreUsuario = ?1")
    Usuario findByNombreUsuario(String nombreUsuario);
}
