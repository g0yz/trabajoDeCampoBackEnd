package com.grupo7.TrabajoDeCampo.dto.equipo;

import java.sql.Timestamp;

public class EquipoRequest {

    private String denominacion;
    private Timestamp fechaIncorporacion;
    private Double montoInvertido;
    private String descripcion;


    public EquipoRequest(String denominacion, Timestamp fechaIncorporacion, Double montoInvertido, String descripcion) {
        this.denominacion = denominacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.montoInvertido = montoInvertido;
        this.descripcion = descripcion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Timestamp getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(Timestamp fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public Double getMontoInvertido() {
        return montoInvertido;
    }

    public void setMontoInvertido(Double montoInvertido) {
        this.montoInvertido = montoInvertido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
