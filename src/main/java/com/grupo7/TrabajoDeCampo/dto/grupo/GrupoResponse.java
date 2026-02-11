package com.grupo7.TrabajoDeCampo.dto.grupo;

import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;

public class GrupoResponse {

    private Long oidGrupo;
    private String facultadRegional;
    private String nombreGrupo;
    private String sigla;
    private String email;
    private String organigrama;
    private String objetivoYDesarollo;

    public GrupoResponse(Grupo grupo) {
        this.oidGrupo = grupo.getOidGrupo();
        this.facultadRegional = grupo.getFacultadRegional();
        this.nombreGrupo = grupo.getNombreGrupo();
        this.sigla = grupo.getSigla();
        this.email = grupo.getEmail();
        this.organigrama = grupo.getOrganigrama();
        this.objetivoYDesarollo = grupo.getObjetivoYDesarollo();
    }


    public String getFacultadRegional() {
        return facultadRegional;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public String getSigla() {
        return sigla;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganigrama() {
        return organigrama;
    }

    public String getObjetivoYDesarollo() {
        return objetivoYDesarollo;
    }

    public Long getOidGrupo() {
        return oidGrupo;
    }
}