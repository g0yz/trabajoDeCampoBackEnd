package com.grupo7.TrabajoDeCampo.model.memoria;


import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import jakarta.persistence.*;
@Entity
@Table(name = "MemoriaDocumento")
public class MemoriaDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oidMemoriaDocumento;

    @ManyToOne
    @JoinColumn(name = "oidMemoria", nullable = false)
    private Memoria memoria;

    // ===== DATOS COPIADOS DEL DOCUMENTO =====

    @Column(nullable = false)
    private Long oidDocumento;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String autores;

    @Column
    private String editorial;

    @Column
    private Integer anio;


    // ===== CONSTRUCTORES =====

    public MemoriaDocumento() {}

    public MemoriaDocumento(Memoria memoria, Documento documento) {
        this.memoria = memoria;
        this.oidDocumento = documento.getOidDocumento();
        this.titulo = documento.getTitulo();
        this.autores = documento.getAutores();
        this.editorial = documento.getEditorial();
        this.anio = documento.getAnio();
    }

    // ===== GETTERS =====

    public Long getOidMemoriaDocumento() {
        return oidMemoriaDocumento;
    }

    public Memoria getMemoria() {
        return memoria;
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

}