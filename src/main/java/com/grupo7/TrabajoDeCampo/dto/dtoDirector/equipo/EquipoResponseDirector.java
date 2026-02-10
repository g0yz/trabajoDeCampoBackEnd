package com.grupo7.TrabajoDeCampo.dto.dtoDirector.equipo;

import java.sql.Timestamp;

public class EquipoResponseDirector {

    private Long oidEquipo;
    private String denominacion;
    private Timestamp fechaIncorporacion;
    private Double montoInvertido;
    private String descripcion;
    private Boolean activo;

    public EquipoResponseDirector(Long oidEquipo, String denominacion, Timestamp fechaIncorporacion, Double montoInvertido, String descripcion, Boolean activo) {
        this.oidEquipo = oidEquipo;
        this.denominacion = denominacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.montoInvertido = montoInvertido;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Long getOidEquipo() { return oidEquipo; }
    public String getDenominacion() { return denominacion; }
    public Timestamp getFechaIncorporacion() { return fechaIncorporacion; }
    public Double getMontoInvertido() { return montoInvertido; }
    public String getDescripcion() { return descripcion; }
    public Boolean getActivo() { return activo; }
}
