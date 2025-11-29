package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "IntegranteConsejoEducativo")
public class IntegranteConsejoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidIntegranteConsejoEducativo")
    private Long oidIntegranteConsejoEducativo;

    @ManyToOne
    @JoinColumn(name = "oidCargo")
    private Cargo Cargo;

    @OneToOne
    @JoinColumn(name = "oidPersona", referencedColumnName = "oidPersona", nullable = false)
    private Persona persona;


    public IntegranteConsejoEducativo() {
    }

    public IntegranteConsejoEducativo(Long oidIntegranteConsejoEducativo, Cargo cargo, Persona persona) {
        this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
        Cargo = cargo;
        this.persona = persona;
    }

    public Long getOidIntegranteConsejoEducativo() {
        return oidIntegranteConsejoEducativo;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setOidIntegranteConsejoEducativo(Long oidIntegranteConsejoEducativo) {
        this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
    }

    public void setCargo(Cargo cargo) {
        Cargo = cargo;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
