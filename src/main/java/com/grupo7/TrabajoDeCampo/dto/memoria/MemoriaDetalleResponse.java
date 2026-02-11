package com.grupo7.TrabajoDeCampo.dto.memoria;

import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;

import java.sql.Timestamp;
import java.util.List;

public class MemoriaDetalleResponse {

        private Long oidMemoria;

        private Integer anio;

        private Timestamp fechaCreacion;

        private Grupo grupo;

        private List<MemoriaPersonaResponse> personas;

        private List<MemoriaDocumentoResponse> documentos;

        private List<MemoriaEquipoResponse> equipos;


    public MemoriaDetalleResponse(
            Long oidMemoria,
            Integer anio,
            Timestamp fechaCreacion,
            Grupo grupo,
            List<MemoriaDocumentoResponse> documentos,
            List<MemoriaEquipoResponse> equipos,
            List<MemoriaPersonaResponse> personas
    ) {
        this.oidMemoria = oidMemoria;
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
        this.grupo = grupo;
        this.documentos = documentos;
        this.equipos = equipos;
        this.personas = personas;
    }

        public Long getOidMemoria() { return oidMemoria; }
        public Integer getAnio() { return anio; }
        public Timestamp getFechaCreacion() { return fechaCreacion; }
        public List<MemoriaDocumentoResponse> getDocumentos() { return documentos; }
        public List<MemoriaEquipoResponse> getEquipos() { return equipos; }
        public List<MemoriaPersonaResponse> getPersonas() { return personas; }

    public Grupo getGrupo() {
        return grupo;
    }
}
