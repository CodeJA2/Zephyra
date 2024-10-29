package com.proyecto.Zephyra.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.entidades.Marca;
import com.proyecto.Zephyra.repositorios.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    // C - Create (Crear):
    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    // R - Read (Leer):
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    // U - Update (Actualizar):
    public Marca actualizarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    // D - Delete (Eliminar):
    public void eliminarMarca(Long id) {
        marcaRepository.deleteById(id);
    }

    // Obtener una Marca segun su ID
    public Marca obtenerMarcaPorId(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }
}
