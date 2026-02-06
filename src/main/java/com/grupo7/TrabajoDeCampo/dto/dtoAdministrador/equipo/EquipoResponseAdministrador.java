package com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.equipo;

import java.sql.Timestamp;

public class EquipoResponseAdministrador {

    private Long oidEquipo;
    private String denominacion;
    private Timestamp fechaIncorporacion;
    private Double montoInvertido;
    private String descripcion;
    private Boolean activo;

    // info mínima del grupo
    private Long oidGrupo;
    private String nombreGrupo;

    public EquipoResponseAdministrador(
            Long oidEquipo,
            String denominacion,
            Timestamp fechaIncorporacion,
            Double montoInvertido,
            String descripcion,
            Boolean activo,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidEquipo = oidEquipo;
        this.denominacion = denominacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.montoInvertido = montoInvertido;
        this.descripcion = descripcion;
        this.activo = activo;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidEquipo() {
        return oidEquipo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public Timestamp getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public Double getMontoInvertido() {
        return montoInvertido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Long getOidGrupo() {
        return oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }
}
