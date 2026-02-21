package com.grupo7.TrabajoDeCampo.model.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaEquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaPersonaResponse;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;


import java.sql.Timestamp;
import java.util.List;

public class MemoriaDetalle {

    private Long oidMemoria;

    private List<MemoriaPersona> personas;

    private List<MemoriaDocumento> documentos;

    private List<MemoriaEquipo> equipos;


    public MemoriaDetalle(
            Long oidMemoria,
            List<MemoriaDocumento> documentos,
            List<MemoriaEquipo> equipos,
            List<MemoriaPersona> personas
    ) {
        this.oidMemoria = oidMemoria;
        this.documentos = documentos;
        this.equipos = equipos;
        this.personas = personas;
    }

    public Long getOidMemoria() { return oidMemoria; }
    public List<MemoriaDocumento> getDocumentos() { return documentos; }
    public List<MemoriaEquipo> getEquipos() { return equipos; }
    public List<MemoriaPersona> getPersonas() { return personas; }

}

