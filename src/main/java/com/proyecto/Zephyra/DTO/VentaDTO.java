package com.proyecto.Zephyra.DTO;

import java.util.List;


import lombok.Data;

@Data
public class VentaDTO {
    private Integer userId;
    private List<DetalleVentaDTO> detalles;
    private Double total;
}
