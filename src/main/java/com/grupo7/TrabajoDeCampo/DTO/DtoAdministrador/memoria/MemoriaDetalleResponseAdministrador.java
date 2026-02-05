package com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.memoria;

import java.time.Instant;
import java.util.List;

public class MemoriaDetalleResponseAdministrador {

    private Long oidMemoria;
    private Integer anio;
    private Instant fechaCreacion;

    private Long oidGrupo;
    private String nombreGrupo;


    private List <MemoriaPersonaResponseAdministrador> personas;
    private List <MemoriaDocumentoResponseAdministrador> documentos;
    private List <MemoriaEquipoResponseAdministrador> equipos;


    public MemoriaDetalleResponseAdministrador(Long oidMemoria,Integer anio, Instant fechaCreacion, Long oidGrupo, String nombreGrupo, List<MemoriaPersonaResponseAdministrador> personas, List<MemoriaDocumentoResponseAdministrador> documentos, List<MemoriaEquipoResponseAdministrador> equipos) {
        this.oidMemoria = oidMemoria;
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
        this.personas = personas;
        this.documentos = documentos;
        this.equipos = equipos;
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

    public List<MemoriaPersonaResponseAdministrador> getPersonas() {
        return personas;
    }

    public List<MemoriaDocumentoResponseAdministrador> getDocumentos() {
        return documentos;
    }

    public List<MemoriaEquipoResponseAdministrador> getEquipos() {
        return equipos;
    }
}
