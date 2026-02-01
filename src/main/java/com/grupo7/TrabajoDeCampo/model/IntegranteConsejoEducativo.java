package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "IntegranteConsejoEducativo")
public class IntegranteConsejoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidIntegranteConsejoEducativo")
    private Long oidIntegranteConsejoEducativo;


    @Column(name = "cargo")
    private String cargo;

    @Column(name = "activo" , nullable = false)
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name = "oidPersona" , unique = true , nullable = false)
    private Persona persona;


    public IntegranteConsejoEducativo() {
    }

    public IntegranteConsejoEducativo( String cargo, Persona persona) {
        this.cargo = cargo;
        this.persona = persona;
        this.activo = true;
    }

    public Long getOidIntegranteConsejoEducativo() {
        return oidIntegranteConsejoEducativo;
    }

    public String getCargo() {
        return cargo;
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

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
