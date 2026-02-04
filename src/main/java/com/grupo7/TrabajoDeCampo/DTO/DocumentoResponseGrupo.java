package com.grupo7.TrabajoDeCampo.DTO;

public class DocumentoResponseGrupo {

        private Long oidDocumento;
        private String titulo;
        private String autores;
        private String editorial;
        private Integer anio;
        private String nombreArchivo;
        private String tipoArchivo;
        private Boolean activo;

        public DocumentoResponseGrupo(
                Long oidDocumento,
                String titulo,
                String autores,
                String editorial,
                Integer anio,
                String nombreArchivo,
                String tipoArchivo,
                Boolean activo) {

            this.oidDocumento = oidDocumento;
            this.titulo = titulo;
            this.autores = autores;
            this.editorial = editorial;
            this.anio = anio;
            this.nombreArchivo = nombreArchivo;
            this.tipoArchivo = tipoArchivo;
            this.activo = true;
        }

        public Long getOidDocumento() { return oidDocumento; }
        public String getTitulo() { return titulo; }
        public String getAutores() { return autores; }
        public String getEditorial() { return editorial; }
        public Integer getAnio() { return anio; }
        public String getNombreArchivo() { return nombreArchivo; }
        public String getTipoArchivo() { return tipoArchivo; }
        public Boolean getActivo() { return activo; }
    }

