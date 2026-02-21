package com.grupo7.TrabajoDeCampo.dto.memoria;

import java.sql.Timestamp;
import java.time.Instant;

public class MemoriaResponse {

    private Long oidMemoria;
    private Integer anio;

    private Long oidGrupo;
    private String nombreGrupo;

    public MemoriaResponse(
            Long oidMemoria,
            Integer anio,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidMemoria = oidMemoria;
        this.anio = anio;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public MemoriaResponse(Long oidMemoria, Integer anio, Timestamp fechaCreacion) {
        this.oidMemoria = oidMemoria;
        this.anio = anio;
    }


    public Long getOidMemoria() {
        return oidMemoria;
    }

    public Integer getAnio() {
        return anio;
    }


    public Long getOidGrupo() {
        return oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }
}
