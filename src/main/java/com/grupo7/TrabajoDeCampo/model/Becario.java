package com.grupo7.TrabajoDeCampo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Becario")
public class Becario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "oidBecario")
    private Long oidBecario;

    @Column(name = "fuenteFinanciamiento")
    private String fuenteFinanciamiento;

    @Column(name = "tipoBecario")
    @Enumerated(EnumType.STRING)
    private TipoBecario tipoBecario;

    @OneToOne
    @JoinColumn(name = "oidPersona" , unique = true , nullable = false)
    private Persona persona;


    public Becario() {
    }

    public Becario(Long oidBecario, String fuenteFinanciamiento, TipoBecario tipoBecario, Persona persona) {
        this.oidBecario = oidBecario;
        this.fuenteFinanciamiento = fuenteFinanciamiento;
        this.tipoBecario = tipoBecario;
        this.persona = persona;
    }

    public Becario(TipoBecario tipoBecario, String fuenteFinanciamiento) {
        this.tipoBecario = tipoBecario;
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public Long getOidBecario() {
        return oidBecario;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public TipoBecario getTipoBecario() {
        return tipoBecario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setOidBecario(Long oidBecario) {
        this.oidBecario = oidBecario;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public void setTipoBecario(TipoBecario tipoBecario) {
        this.tipoBecario = tipoBecario;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
