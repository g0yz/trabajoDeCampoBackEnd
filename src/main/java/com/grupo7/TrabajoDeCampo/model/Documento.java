package com.grupo7.TrabajoDeCampo.model;

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

    @ManyToOne
    @JoinColumn(name="oidGrupo", referencedColumnName = "oidGrupo", nullable = false)
    private Grupo grupo;

    //CONSTRUCTORES
    public Documento() {
    }

    public Documento(String autores, String editorial, Integer anio, String titulo) {
        this.autores = autores;
        this.editorial = editorial;
        this.anio = anio;
        this.titulo = titulo;
    }

    //GETTERS
    public long getOidDocumento() {
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

    public void setOidDocumento(Long oidDocumento) {
        this.oidDocumento = oidDocumento;
    }



}
