package com.grupo7.TrabajoDeCampo.model.documento;

import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import jakarta.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidDocumento")
    private Long oidDocumento;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autores")
    private String autores;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "anio")
    private Integer anio;

    @Lob
    @Column(name = "archivo_base64", columnDefinition = "TEXT")
    private String archivoBase64;


    @Column(name = "activo")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name="oidGrupo", referencedColumnName = "oidGrupo", nullable = false)
    private Grupo grupo;

    //CONSTRUCTORES
    public Documento() {
    }


    public Documento(String titulo, String autores, String editorial, Integer anio, String archivoBase64, Grupo grupo) {
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
        this.archivoBase64 = archivoBase64;
        this.activo = true;
        this.grupo = grupo;
    }

    //GETTERS
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

    public Grupo getGrupo() {
        return grupo;
    }


    public String getArchivoBase64() {
        return archivoBase64;
    }

    public void setArchivoBase64(String archivoBase64) {
        this.archivoBase64 = archivoBase64;
    }


    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    //SETTERS
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }




}
