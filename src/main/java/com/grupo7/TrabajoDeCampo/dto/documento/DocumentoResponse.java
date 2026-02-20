package com.grupo7.TrabajoDeCampo.dto.documento;


public class DocumentoResponse {

    private Long oidDocumento;
    private String titulo;
    private String autores;
    private String editorial;
    private Integer anio;
    private Boolean activo;


    // info mínima del grupo
    private Long oidGrupo;
    private String nombreGrupo;

    private String archivoBase64;
    private String nombreArchivo;

    public DocumentoResponse(
            Long oidDocumento,
            String titulo,
            String autores,
            String editorial,
            Integer anio,
            Boolean activo,
            Long oidGrupo,
            String nombreGrupo,
            String archivoBase64,
            String nombreArchivo
    ) {
        this.oidDocumento = oidDocumento;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
        this.activo = activo;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
        this.archivoBase64 = archivoBase64;
        this.nombreArchivo = nombreArchivo;
    }

    public Long getOidDocumento() { return oidDocumento; }
    public String getTitulo() { return titulo; }
    public String getAutores() { return autores; }
    public String getEditorial() { return editorial; }
    public Integer getAnio() { return anio; }
    public Boolean getActivo() { return activo; }
    public Long getOidGrupo() { return oidGrupo; }
    public String getNombreGrupo() { return nombreGrupo; }
    public String getArchivoBase64() {
        return archivoBase64;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }
}
