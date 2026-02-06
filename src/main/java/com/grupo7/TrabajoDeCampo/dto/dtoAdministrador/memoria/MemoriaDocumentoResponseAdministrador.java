package com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.memoria;

public class MemoriaDocumentoResponseAdministrador {

    private Long oidDocumento;
    private String titulo;
    private String autores;
    private String editorial;
    private Integer anio;
    private Boolean activo;

    public MemoriaDocumentoResponseAdministrador(
            Long oidDocumento,
            String titulo,
            String autores,
            String editorial,
            Integer anio,
            Boolean activo) {

        this.oidDocumento = oidDocumento;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
        this.activo = activo;
    }

    public Long getOidDocumento() {
        return oidDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public String getEditorial() {
        return editorial;
    }

    public Integer getAnio() {
        return anio;
    }

    public Boolean getActivo() {
        return activo;
    }
}
