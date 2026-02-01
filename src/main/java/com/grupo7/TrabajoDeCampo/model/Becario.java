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

    @Column(name = "activo")
    private Boolean activo = true;


    @OneToOne
    @JoinColumn(name = "oidPersona" , unique = true , nullable = false)
    private Persona persona;


    public Becario() {
    }

    public Becario( String fuenteFinanciamiento, TipoBecario tipoBecario, Persona persona) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
        this.tipoBecario = tipoBecario;
        this.persona = persona;
        this.activo = true;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
