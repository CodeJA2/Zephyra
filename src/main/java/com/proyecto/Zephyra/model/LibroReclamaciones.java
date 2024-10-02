package com.proyecto.Zephyra.model;

public class LibroReclamaciones {
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String departamento;
    private String provincia;
    private String distrito;
    private String telefono;
    private String correoElectronico;
    private String tipoReclamo;
    private String fechaCompra;
    private String numeroBoleta;
    private String detalleReclamo;

    // Getters y Setters
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTipoReclamo() { return tipoReclamo; }
    public void setTipoReclamo(String tipoReclamo) { this.tipoReclamo = tipoReclamo; }

    public String getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getNumeroBoleta() { return numeroBoleta; }
    public void setNumeroBoleta(String numeroBoleta) { this.numeroBoleta = numeroBoleta; }

    public String getDetalleReclamo() { return detalleReclamo; }
    public void setDetalleReclamo(String detalleReclamo) { this.detalleReclamo = detalleReclamo; }
}
