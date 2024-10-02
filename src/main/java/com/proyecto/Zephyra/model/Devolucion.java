package com.proyecto.Zephyra.model;

import org.springframework.web.multipart.MultipartFile;

public class Devolucion {
    private String nombreCompleto;
    private String apellidoCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private String motivoDevolucion;
    private MultipartFile[] evidencia;

    // Getters y Setters
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

    public MultipartFile[] getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(MultipartFile[] evidencia) {
        this.evidencia = evidencia;
    }
}
