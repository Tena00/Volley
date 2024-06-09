package com.manuel.tfg.daos;

import com.manuel.tfg.daos.model.ZonasAtaque;
import com.manuel.tfg.daos.model.ZonasCampo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioZonasAtaque extends JpaRepository<ZonasAtaque,Integer> {
}
