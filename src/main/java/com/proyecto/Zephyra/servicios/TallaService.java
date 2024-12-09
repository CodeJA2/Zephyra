package com.proyecto.Zephyra.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.model.Talla;
import com.proyecto.Zephyra.repositorios.TallaRepository;


@Service
public class TallaService {

    @Autowired
    private TallaRepository tallaRepository;

     // C - Create (Crear):
    public Talla guardarTalla(Talla Talla) {
        return tallaRepository.save(Talla);
    }

    // R - Read (Leer):
    public List<Talla> listarTallas() {
        return tallaRepository.findAll();
    }

    // U - Update (Actualizar):
    public Talla actualizarTalla(Talla Talla) {
        return tallaRepository.save(Talla);
    }

    // D - Delete (Eliminar):
    public void eliminarTalla(Integer id) {
        tallaRepository.deleteById(id);
    }

    // Obtener una Talla segun su ID
    public Talla obtenerTallaPorId(Integer id) {
        return tallaRepository.findById(id).orElse(null);
    }

    // conteo de todas las Tallas registradas:
    public long contarTallas() {
        return tallaRepository.count();
    }

    
}
