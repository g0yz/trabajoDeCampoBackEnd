package com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.memoria;

import java.time.Instant;
import java.util.List;

public class MemoriaDetalleResponseIntegrante {

    private final Long oidMemoria;
    private final Integer anio;
    private final Instant fechaCreacion;

    private final List<MemoriaDocumentoResponseIntegrante> documentos;
    private final List<MemoriaEquipoResponseIntegrante> equipos;
    private final List<MemoriaPersonaResponseIntegrante> personas;

    public MemoriaDetalleResponseIntegrante(
            Long oidMemoria,
            Integer anio,
            Instant fechaCreacion,
            List<MemoriaDocumentoResponseIntegrante> documentos,
            List<MemoriaEquipoResponseIntegrante> equipos,
            List<MemoriaPersonaResponseIntegrante> personas
    ) {
        this.oidMemoria = oidMemoria;
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
        this.documentos = documentos;
        this.equipos = equipos;
        this.personas = personas;
    }

    public Long getOidMemoria() { return oidMemoria; }
    public Integer getAnio() { return anio; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public List<MemoriaDocumentoResponseIntegrante> getDocumentos() { return documentos; }
    public List<MemoriaEquipoResponseIntegrante> getEquipos() { return equipos; }
    public List<MemoriaPersonaResponseIntegrante> getPersonas() { return personas; }
}
