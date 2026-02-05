package com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
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

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name = "oidPersona" , unique = true , nullable = false)
    private Persona persona;


    public Personal() {
    }

    public Personal(TipoPersonal tipoPersonal, Persona persona) {
        this.tipoPersonal = tipoPersonal;
        this.persona = persona;
        this.activo = true;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setTipoPersonal(TipoPersonal tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}