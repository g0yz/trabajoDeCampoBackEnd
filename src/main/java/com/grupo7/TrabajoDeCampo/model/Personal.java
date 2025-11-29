package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidPersonal")
    private Long oidPersonal;


    @Enumerated(EnumType.STRING)
    @Column(name="tipoPersonal")
    private TipoPersonal tipoPersonal;

    @OneToOne
    @JoinColumn(name = "oidPersona", referencedColumnName = "oidPersona")
    private Persona persona;


    public Personal() {
    }

    public Personal(Long oidPersonal, TipoPersonal tipoPersonal, Persona persona) {
        this.oidPersonal = oidPersonal;
        this.tipoPersonal = tipoPersonal;
        this.persona = persona;
    }


    public Long getOidPersonal() {
        return oidPersonal;
    }

    public TipoPersonal getTipoPersonal() {
        return tipoPersonal;
    }

    public Persona getPersona() {
        return persona;
    }


    public void setOidPersonal(Long oidPersonal) {
        this.oidPersonal = oidPersonal;
    }

    public void setTipoPersonal(TipoPersonal tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
