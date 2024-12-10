package com.proyecto.Zephyra.model;

import jakarta.persistence.*;

@Entity
@Table(name = "devoluciones")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 50)
    private String nombreCompleto;

    @Column(name = "apellido_completo", nullable = false, length = 50)
    private String apellidoCompleto;

    @Column(name = "correo_electronico", nullable = false, length = 50)
    private String correoElectronico;

    @Column(name = "numero_telefono", nullable = false, length = 15)
    private String numeroTelefono;

    @Column(name = "motivo_devolucion", nullable = false, length = 500)
    private String motivoDevolucion;

    @Column(name = "evidencia", nullable = true)
    private String evidencia; // Se almacenará como una ruta o nombre de archivo.

    // Constructor sin argumentos
    public Devolucion() {
    }

    // Constructor con todos los campos excepto ID
    public Devolucion(String nombreCompleto, String apellidoCompleto, String correoElectronico, String numeroTelefono, String motivoDevolucion, String evidencia) {
        this.nombreCompleto = nombreCompleto;
        this.apellidoCompleto = apellidoCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.motivoDevolucion = motivoDevolucion;
        this.evidencia = evidencia;
    }

    // Constructor con todos los campos
    public Devolucion(Long id, String nombreCompleto, String apellidoCompleto, String correoElectronico, String numeroTelefono, String motivoDevolucion, String evidencia) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.apellidoCompleto = apellidoCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.motivoDevolucion = motivoDevolucion;
        this.evidencia = evidencia;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellidoCompleto() {
        return apellidoCompleto;
    }

    public void setApellidoCompleto(String apellidoCompleto) {
        this.apellidoCompleto = apellidoCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }

    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    @Override
    public String toString() {
        return "Devolucion [id=" + id + ", nombreCompleto=" + nombreCompleto + ", apellidoCompleto=" + apellidoCompleto +
                ", correoElectronico=" + correoElectronico + ", numeroTelefono=" + numeroTelefono +
                ", motivoDevolucion=" + motivoDevolucion + ", evidencia=" + evidencia + "]";
    }
}
