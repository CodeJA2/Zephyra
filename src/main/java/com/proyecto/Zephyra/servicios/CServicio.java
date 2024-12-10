package com.proyecto.Zephyra.servicios;

import com.proyecto.Zephyra.model.Contactanos;
import java.util.List;

public interface CServicio {
    
    	public List<Contactanos> listarTodosLosContactanos();
	
	public Contactanos guardarContactanos(Contactanos contactanos);
	
	public Contactanos obtenerContactanosPorId(Long id);
	
	public Contactanos actualizarContactanos(Contactanos contactanos);
	
	public void eliminarContactanos(Long id);
    
}
