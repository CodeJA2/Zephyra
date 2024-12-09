package com.proyecto.Zephyra.DTO;

import lombok.Data;

@Data
public class CarritoRequest {
    private Integer productoId;
    private Integer tallaId;
    private int cantidad;
}
