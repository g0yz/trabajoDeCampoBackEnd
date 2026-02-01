package com.grupo7.TrabajoDeCampo.model;


import jakarta.persistence.*;


@Entity
@Table (name = "MemoriaEquipo")
public class MemoriaEquipo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long oidMemoriaEquipo;

    @ManyToOne
    @JoinColumn( name = "oidMemoria" , nullable = false )
    private Memoria memoria;

    @ManyToOne
    @JoinColumn (name = "oidEquipo" , nullable = false)
    private  Equipo equipo;


    public MemoriaEquipo(){}

    public MemoriaEquipo(Memoria memoria, Equipo equipo) {
        this.memoria = memoria;
        this.equipo = equipo;
    }

    public Long getOidMemoriaEquipo() {
        return oidMemoriaEquipo;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
