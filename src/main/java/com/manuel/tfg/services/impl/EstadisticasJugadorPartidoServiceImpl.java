package com.manuel.tfg.services.impl;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.services.EstadisticasJugadorPartidoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadisticasJugadorPartidoServiceImpl implements EstadisticasJugadorPartidoService {


    private RepositorioEstadisticas estadisticasRepository;

    public EstadisticasJugadorPartidoServiceImpl(RepositorioEstadisticas estadisticasRepository) {
        this.estadisticasRepository = estadisticasRepository;
    }

    public List<EstadisticasJugador> todasEstadisticas(){
        return estadisticasRepository.findAll();
    }

    public EstadisticasJugador obtenerEstadisticasPorId(Integer id) {
        Optional<EstadisticasJugador> estadisticasOptional = estadisticasRepository.findById(id);
        return estadisticasOptional.orElse(null);
    }

    public List<EstadisticasJugador> obtenerEstadisticasPorIdJugador(Integer idJugador) {
        List<EstadisticasJugador> estadisticasJugador = estadisticasRepository.findByIdJugador(idJugador);
        return estadisticasJugador;
    }

    public EstadisticasJugador crearEstadisticas(EstadisticasJugador estadisticas) {
        return estadisticasRepository.save(estadisticas);
    }

    public EstadisticasJugador actualizarEstadisticas(Integer id, EstadisticasJugador estadisticas) {
        estadisticas.setIdEstadisticasJugadorPartido(id);
        return estadisticasRepository.save(estadisticas);
    }

    public void eliminarEstadisticas(Integer id) {
        estadisticasRepository.deleteById(id);
    }
}


