package com.grupo7.TrabajoDeCampo.DTO;

import java.sql.Timestamp;

public class GrupoResponse {

    private Long oidGrupo;
    private String nombreGrupo;
    private String sigla;
    private String facultadRegional;

    public GrupoResponse(
            Long oidGrupo,
            String nombreGrupo,
            String sigla,
            String facultadRegional) {
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
        this.sigla = sigla;
        this.facultadRegional = facultadRegional;
    }

    public Long getOidGrupo() {
        return oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public String getSigla() {
        return sigla;
    }

    public String getFacultadRegional() {
        return facultadRegional;
    }

    public static class MemoriaResponse {

        private Long oidMemoria;
        private Integer anio;
        private Timestamp fechaCreacion;

        public MemoriaResponse(
                Long oidMemoria,
                Integer anio,
                Timestamp fechaCreacion) {
            this.oidMemoria = oidMemoria;
            this.anio = anio;
            this.fechaCreacion = fechaCreacion;
        }

        public Long getOidMemoria() {
            return oidMemoria;
        }

        public Integer getAnio() {
            return anio;
        }

        public Timestamp getFechaCreacion() {
            return fechaCreacion;
        }
    }
}
