package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "IntegranteConsejoEducativo")
public class IntegranteConsejoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidIntegranteConsejoEducativo")
    private Long oidIntegranteConsejoEducativo;


    @Column(name = "Cargo")
    private String cargo;

    @OneToOne
    @JoinColumn(name = "oidPersona", referencedColumnName = "oidPersona", nullable = false)
    private Persona persona;


    public IntegranteConsejoEducativo() {
    }

    public IntegranteConsejoEducativo(Long oidIntegranteConsejoEducativo, String cargo, Persona persona) {
        this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
        this.cargo = cargo;
        this.persona = persona;
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

    public void setOidIntegranteConsejoEducativo(Long oidIntegranteConsejoEducativo) {
        this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
