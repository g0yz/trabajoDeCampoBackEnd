package com.grupo7.TrabajoDeCampo.model;

import jakarta.persistence.*;

@Entity
@Table(name ="Cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "oidCargo")
    private Long oidCargo;

    @Column (name = "descripcion")
    private String descripcion;


    @OneToOne
    @JoinColumn(name = "oidIntegranteConsejoEducativo", referencedColumnName = "oidIntegranteConsejoEducativo")
    private IntegranteConsejoEducativo integranteConsejoEducativo;


    public Cargo() {
    }

    public Cargo(Long oidCargo, String descripcion, IntegranteConsejoEducativo integranteConsejoEducativo) {
        this.oidCargo = oidCargo;
        this.descripcion = descripcion;
        this.integranteConsejoEducativo = integranteConsejoEducativo;
    }


    public Long getOidCargo() {
        return oidCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public IntegranteConsejoEducativo getIntegranteConsejoEducativo() {
        return integranteConsejoEducativo;
    }

    public void setOidCargo(Long oidCargo) {
        this.oidCargo = oidCargo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIntegranteConsejoEducativo(IntegranteConsejoEducativo integranteConsejoEducativo) {
        this.integranteConsejoEducativo = integranteConsejoEducativo;
    }
}
