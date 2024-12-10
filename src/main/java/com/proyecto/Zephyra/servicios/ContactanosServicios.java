package com.proyecto.Zephyra.servicios;

import com.proyecto.Zephyra.model.Contactanos;
import com.proyecto.Zephyra.repositorios.ContactanosRepositorioDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactanosServicios implements CServicio{

	@Autowired
	private ContactanosRepositorioDAO repositorio;

	@Override
	public List<Contactanos> listarTodosLosContactanos() {
		return repositorio.findAll();
	}

	@Override
	public Contactanos guardarContactanos(Contactanos contactanos) {
		return repositorio.save(contactanos);
	}

	@Override
	public Contactanos obtenerContactanosPorId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Contactanos actualizarContactanos(Contactanos contactanos) {
		return repositorio.save(contactanos);
	}

	@Override
	public void eliminarContactanos(Long id) {
		repositorio.deleteById(id);

	}

}

