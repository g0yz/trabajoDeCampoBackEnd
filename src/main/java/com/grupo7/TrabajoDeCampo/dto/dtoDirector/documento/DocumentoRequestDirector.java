package com.grupo7.TrabajoDeCampo.dto.dtoDirector.documento;

public class DocumentoRequestDirector {

    private String titulo;
    private String autores;
    private String editorial;
    private Integer anio;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutores() { return autores; }
    public void setAutores(String autores) { this.autores = autores; }
    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
}
