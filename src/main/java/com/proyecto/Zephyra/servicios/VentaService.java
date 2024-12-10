package com.proyecto.Zephyra.servicios;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.DTO.DetalleVentaDTO;
import com.proyecto.Zephyra.DTO.VentaDTO;
import com.proyecto.Zephyra.model.DetalleVenta;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.model.Venta;
import com.proyecto.Zephyra.repositorios.DetalleVentaRepository;
import com.proyecto.Zephyra.repositorios.ProductoRepository;
import com.proyecto.Zephyra.repositorios.UserRepository;
import com.proyecto.Zephyra.repositorios.VentaRepository;


@Service
public class VentaService {
     @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public void crearVenta(VentaDTO ventaDTO) {
        // Busca al usuario en la base de datos
        User usuario = userRepository.findById(ventaDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Crear la venta
        Venta venta = new Venta();
        venta.setUsuario(usuario);
        venta.setTotal(ventaDTO.getTotal());
        venta.setSaleDate(LocalDateTime.now());
        
        // Guarda la venta
        ventaRepository.save(venta);
    
        // Crea y guarda los detalles de la venta
        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecio());
            detalle.setTotalProducto(detalleDTO.getCantidad() * detalleDTO.getPrecio());
    
            detalleVentaRepository.save(detalle);
        }
    }
}
