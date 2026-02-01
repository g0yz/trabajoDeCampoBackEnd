package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "MemoriaPersona")
public class MemoriaPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oidMemoriaPersona;

    @ManyToOne
    @JoinColumn(name = "oidMemoria", nullable = false)
    private Memoria memoria;

    @ManyToOne
    @JoinColumn(name = "oidPersona", nullable = false)
    private Persona persona;

    @Column(name = "rolEnMemoria")
    private String rolEnMemoria;

    @Column(name = "horasSemanales")
    private Integer horasSemanales;

    public MemoriaPersona(){}

    public MemoriaPersona(Memoria memoria, Persona persona, String rolEnMemoria, Integer horasSemanales) {
        this.memoria = memoria;
        this.persona = persona;
        this.rolEnMemoria = rolEnMemoria;
        this.horasSemanales = horasSemanales;
    }


    public Long getOidMemoriaPersona() {
        return oidMemoriaPersona;
    }


    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getRolEnMemoria() {
        return rolEnMemoria;
    }

    public void setRolEnMemoria(String rolEnMemoria) {
        this.rolEnMemoria = rolEnMemoria;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }
}
