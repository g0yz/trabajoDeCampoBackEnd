package com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.documento;

public class DocumentoResponseAdministrador {

    private Long oidDocumento;
    private String titulo;
    private String autores;
    private String editorial;
    private Integer anio;
    private Boolean activo;

    // info mínima del grupo
    private Long oidGrupo;
    private String nombreGrupo;

    public DocumentoResponseAdministrador(
            Long oidDocumento,
            String titulo,
            String autores,
            String editorial,
            Integer anio,
            Boolean activo,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidDocumento = oidDocumento;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
        this.activo = true;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidDocumento() { return oidDocumento; }
    public String getTitulo() { return titulo; }
    public String getAutores() { return autores; }
    public String getEditorial() { return editorial; }
    public Integer getAnio() { return anio; }
    public Boolean getActivo() { return activo; }
    public Long getOidGrupo() { return oidGrupo; }
    public String getNombreGrupo() { return nombreGrupo; }
}
