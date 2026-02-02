package com.grupo7.TrabajoDeCampo.DTO.Memoria;

import com.grupo7.TrabajoDeCampo.model.TipoPersonaMemoria;

public class MemoriaPersonaResponse {

    private Long oidPersona;
    private String nombre;
    private TipoPersonaMemoria tipoPersonaMemoria;
    private Integer horasSemanales;

    public MemoriaPersonaResponse(
            Long oidPersona,
            String nombre,
            TipoPersonaMemoria tipoPersonaMemoria,
            Integer horasSemanales) {
        this.oidPersona = oidPersona;
        this.nombre = nombre;
        this.tipoPersonaMemoria = tipoPersonaMemoria;
        this.horasSemanales = horasSemanales;
    }

    public Long getOidPersona() {
        return oidPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoPersonaMemoria getTipoPersonaMemoria() {
        return tipoPersonaMemoria;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }
}
