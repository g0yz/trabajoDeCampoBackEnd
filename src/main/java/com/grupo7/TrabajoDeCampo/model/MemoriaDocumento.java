package com.grupo7.TrabajoDeCampo.model;


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

    @ManyToOne
    @JoinColumn(name = "oidDocumento", nullable = false)
    private Documento documento;

    public MemoriaDocumento(){}

    public MemoriaDocumento(Memoria memoria, Documento documento) {
        this.memoria = memoria;
        this.documento = documento;
    }

    public Long getOidMemoriaDocumento() {
        return oidMemoriaDocumento;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
