package com.manuel.tfg.services.impl;
import com.manuel.tfg.daos.RepositorioEstadisticas;
import com.manuel.tfg.daos.model.Acciones;
import com.manuel.tfg.daos.model.EstadisticasJugador;
import com.manuel.tfg.services.AccionesService;
import com.manuel.tfg.services.EstadisticasJugadorPartidoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadisticasJugadorPartidoServiceImpl implements EstadisticasJugadorPartidoService {


    private RepositorioEstadisticas estadisticasRepository;

    private AccionesService accionesService;

    public EstadisticasJugadorPartidoServiceImpl(RepositorioEstadisticas estadisticasRepository, AccionesService accionesService) {
        this.estadisticasRepository = estadisticasRepository;
        this.accionesService = accionesService;
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
        List<Acciones> accionesList = estadisticasRepository.findByIdAccionesEstadisticas(id);
        for(Acciones acciones : accionesList){
            accionesService.eliminarAccion(acciones.getIdAccion());
        }
        estadisticasRepository.deleteById(id);
    }
}


