package com.proyecto.Zephyra.servicios;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.model.Carrito;
import com.proyecto.Zephyra.model.CarritoItem;
import com.proyecto.Zephyra.model.Producto;
import com.proyecto.Zephyra.model.Talla;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.repositorios.CarritoItemRepository;
import com.proyecto.Zephyra.repositorios.CarritoRepository;
import com.proyecto.Zephyra.repositorios.ProductoRepository;
import com.proyecto.Zephyra.repositorios.TallaRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void agregarProductoAlCarrito(User usuario, Integer productoId, Integer tallaId, int cantidad) {
        // Busca el carrito del usuario autenticado
        Carrito carrito = carritoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Obtiene la talla específica del producto
        Talla talla = tallaRepository.findById(tallaId)
                .orElseThrow(() -> new RuntimeException("Talla no encontrada"));

        // Verifica si hay suficiente stock para la talla seleccionada
        if (talla.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para la talla seleccionada");
        }

        // Busca el producto por su ID
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verifica si el producto ya existe en el carrito con la talla seleccionada
        Optional<CarritoItem> existingItem = carrito.getItems().stream()
                .filter(item -> item.getProducto().getId().equals(productoId)
                        && item.getTalla().getId().equals(tallaId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Si ya existe, se actualiza la cantidad
            CarritoItem item = existingItem.get();
            item.setCantidad(item.getCantidad() + cantidad);
            carritoItemRepository.save(item);
        } else {
            // Si no existe, se crea un nuevo CarritoItem
            CarritoItem carritoItem = new CarritoItem();
            carritoItem.setCarrito(carrito);
            carritoItem.setProducto(producto);
            carritoItem.setTalla(talla);
            carritoItem.setCantidad(cantidad);
            // Asignar el precio basado en si está en oferta
            if (producto.isEnOferta() && producto.getPrecioOferta() != null) {
                carritoItem.setPrecio(producto.getPrecioOferta());
            } else if (!producto.isEnOferta()) {
                carritoItem.setPrecio(producto.getPrecio());
            } else {
                throw new RuntimeException("Producto en oferta con precio no válido");
            }

            carritoItemRepository.save(carritoItem);
        }

    }



    public Carrito obtenerCarritoPorUsuario(User usuario) {
        // Obtiene el carrito asociado al usuario
        return carritoRepository.findByUsuario(usuario).orElse(null);
    }

    

    public List<CarritoItem> obtenerItemsDelCarrito(User usuario) {
        Carrito carrito = obtenerCarritoPorUsuario(usuario);
        if (carrito != null) {
            List<CarritoItem> items = carrito.getItems();
            for (CarritoItem item : items) {
                
                if (item.getProducto().getPrecioOferta() != null) {
                    item.setPrecio(item.getProducto().getPrecioOferta()); 
                } else {
                    item.setPrecio(item.getProducto().getPrecio()); 
                }
            }
            return items; 
        }
        return Collections.emptyList(); 
    }
    


    public void eliminarProductoDelCarrito(Integer itemId) {
        CarritoItem item = carritoItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            carritoItemRepository.delete(item);
        } else {
            throw new RuntimeException("Producto no encontrado en el carrito");
        }
    }



    public void disminuirCantidadProducto(Integer itemId) {
        CarritoItem item = carritoItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            if (item.getCantidad() > 1) {
                item.setCantidad(item.getCantidad() - 1);
                carritoItemRepository.save(item);
            } else {
                throw new RuntimeException("La cantidad mínima es 1. No se puede disminuir más.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado en el carrito");
        }
    }

    

    public void aumentarCantidadProducto(Integer itemId) {
        CarritoItem item = carritoItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            Producto producto = productoRepository.findById(item.getProducto().getId()).orElse(null);
            if (producto != null) {
                // Asumiendo que tienes una lista de tallas y quieres obtener el stock de una talla específica
                Talla talla = item.getTalla(); // Obtener la talla específica del carrito
                if (talla != null) {
                    int stockDisponible = talla.getStock(); // Asegúrate de que este método exista en la clase Talla
                    if (item.getCantidad() < stockDisponible) {
                        item.setCantidad(item.getCantidad() + 1);
                        carritoItemRepository.save(item);
                    } else {
                        throw new RuntimeException("No se puede aumentar la cantidad. Stock insuficiente.");
                    }
                } else {
                    throw new RuntimeException("Talla no encontrada.");
                }
            } else {
                throw new RuntimeException("Producto no encontrado.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado en el carrito");
        }
    }

    public double calcularTotalCarrito(User usuario) {
        Carrito carrito = obtenerCarritoPorUsuario(usuario);
        if (carrito != null) {
            double total = 0;
            for (CarritoItem item : carrito.getItems()) {
                total += item.getPrecio() * item.getCantidad();
            }
            return total;
        }
        return 0.0;
    }
}
