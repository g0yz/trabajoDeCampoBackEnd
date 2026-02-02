package com.grupo7.TrabajoDeCampo.DTO.Memoria;

import com.grupo7.TrabajoDeCampo.model.TipoPersonaMemoria;

public class MemoriaPersonaRequest {

    private TipoPersonaMemoria tipoPersonaMemoria;
    private Integer horasSemanales;

    public TipoPersonaMemoria getTipoPersonaMemoria() {
        return tipoPersonaMemoria;
    }

    public void setTipoPersonaMemoria(TipoPersonaMemoria tipoPersonaMemoria) {
        this.tipoPersonaMemoria = tipoPersonaMemoria;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }
}
