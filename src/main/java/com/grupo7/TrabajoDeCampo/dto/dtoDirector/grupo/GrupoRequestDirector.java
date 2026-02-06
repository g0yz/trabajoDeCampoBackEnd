package com.grupo7.TrabajoDeCampo.dto.dtoDirector.grupo;

public class GrupoRequestDirector {

        private String facultadRegional;
        private String nombreGrupo;
        private String sigla;
        private String email;
        private String organigrama;
        private String objetivoYDesarollo;

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


    public void setFacultadRegional(String facultadRegional) {
        this.facultadRegional = facultadRegional;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganigrama(String organigrama) {
        this.organigrama = organigrama;
    }

    public void setObjetivoYDesarollo(String objetivoYDesarollo) {
        this.objetivoYDesarollo = objetivoYDesarollo;
    }
}


