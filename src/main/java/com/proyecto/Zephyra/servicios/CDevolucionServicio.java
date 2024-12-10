/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.Zephyra.servicios;

import com.proyecto.Zephyra.model.Devolucion;
import java.util.List;

public interface CDevolucionServicio {
    
    public List<Devolucion> listarTodasLasDevoluciones();

    public Devolucion guardarDevolucion(Devolucion devolucion);

    public Devolucion obtenerDevolucionPorId(Long id);

    public Devolucion actualizarDevolucion(Devolucion devolucion);

    public void eliminarDevolucion(Long id);
}
