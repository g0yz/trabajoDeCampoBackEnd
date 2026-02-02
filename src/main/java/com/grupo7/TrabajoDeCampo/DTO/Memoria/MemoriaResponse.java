package com.grupo7.TrabajoDeCampo.DTO;

import java.time.Instant;

public class MemoriaResponse {

    private Long oidMemoria;
    private Integer anio;
    private Instant fechaCreacion;

    private Long oidGrupo;
    private String nombreGrupo;

    public MemoriaResponse(
            Long oidMemoria,
            Integer anio,
            Instant fechaCreacion,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidMemoria = oidMemoria;
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidMemoria() {
        return oidMemoria;
    }

    public Integer getAnio() {
        return anio;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public Long getOidGrupo() {
        return oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }
}
