package com.manuel.tfg.services.impl;

import com.manuel.tfg.daos.RepositorioEquipos;
import com.manuel.tfg.daos.RepositorioJugadores;
import com.manuel.tfg.daos.model.Equipo;
import com.manuel.tfg.daos.model.Jugador;
import com.manuel.tfg.exception.EquipoExistenteExcepcion;
import com.manuel.tfg.exception.JugadorExistenteException;
import com.manuel.tfg.services.EquipoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private RepositorioEquipos repositorioEquipos;

    private RepositorioJugadores repositorioJugadores;

    public EquipoServiceImpl(RepositorioEquipos repositorioEquipos, RepositorioJugadores repositorioJugadores) {
        this.repositorioEquipos = repositorioEquipos;
        this.repositorioJugadores = repositorioJugadores;
    }


    @Override
    public List<Equipo> todosEquipos() {
        List<Equipo> equipos;
        equipos = repositorioEquipos.findAll();
        return equipos;
    }

    @Override
    public void addEquipo(Equipo equipo) throws EquipoExistenteExcepcion {
        List<Equipo> listaEquipos = repositorioEquipos.findAll();
        for (Equipo equi : listaEquipos) {
            if (equipo.getNombreEquipo().equals(equi.getNombreEquipo())) {
                throw new EquipoExistenteExcepcion("El equipo con nombre " + equipo.getNombreEquipo() + " ya existe.");
            }
        }
        repositorioEquipos.save(equipo);
    }

    @Override
    public void eliminarEquipo(Integer id) throws EquipoExistenteExcepcion {
        if (repositorioEquipos.existsById(id)) {
            repositorioEquipos.deleteById(id);
        } else {
            throw new EquipoExistenteExcepcion("El equipo con id " + id + " no existe.");
        }
    }

    @Override
    public List<Jugador> obtenerJugadoresPorEquipo(Integer idEquipo) {
        return repositorioJugadores.findByEquipoIdEquipo(idEquipo);
    }
}
