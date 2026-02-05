package com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.memoria;

import java.sql.Timestamp;

public class MemoriaResponseIntegrante {

    private Integer anio;
    private Timestamp fechaCreacion;

    public MemoriaResponseIntegrante(
            Long oidMemoria,
            Integer anio,
            Timestamp fechaCreacion
    ) {
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
    }


    public Integer getAnio() {
        return anio;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

}
