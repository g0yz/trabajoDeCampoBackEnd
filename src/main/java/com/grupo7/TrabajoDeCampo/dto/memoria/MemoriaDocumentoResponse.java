package com.grupo7.TrabajoDeCampo.dto.memoria;

public class MemoriaDocumentoResponse {

    private Long oidDocumento;
    private String titulo;
    private String autores;
    private String editorial;
    private Integer anio;
    private Boolean activo;

    public MemoriaDocumentoResponse(
            Long oidDocumento,
            String titulo,
            String autores,
            String editorial,
            Integer anio) {

        this.oidDocumento = oidDocumento;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
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
