package com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.TipoPersonaMemoria;

public class MemoriaPersonaRequestAdministrador {

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
