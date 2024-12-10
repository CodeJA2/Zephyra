package com.proyecto.Zephyra.servicios;

import com.proyecto.Zephyra.model.Devolucion;
import com.proyecto.Zephyra.repositorios.DevolucionRepositorioDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevolucionService implements CDevolucionServicio {

    @Autowired
    private DevolucionRepositorioDAO repositorio;

    @Override
    public List<Devolucion> listarTodasLasDevoluciones() {
        return repositorio.findAll();
    }

    @Override
    public Devolucion guardarDevolucion(Devolucion devolucion) {
        return repositorio.save(devolucion);
    }

    @Override
    public Devolucion obtenerDevolucionPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Devolucion actualizarDevolucion(Devolucion devolucion) {
        return repositorio.save(devolucion);
    }

    @Override
    public void eliminarDevolucion(Long id) {
        repositorio.deleteById(id);
    }
}