package com.grupo7.TrabajoDeCampo.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidEquipo")
    private Long oidEquipo;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "fechaIncorporacion")
    private Timestamp fechaIncorporacion;

    @Column(name="montoInvertido")
    private Double montoInvertido;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="oidGrupo", referencedColumnName = "oidGrupo", nullable = false)
    private Grupo grupo;


    //CONSTRUCTORES
    public Equipo() {
    }

    public Equipo(Long oidEquipo, String descripcion, String denominacion, Timestamp fechaIncorporacion, Double montoInvertido) {
        this.oidEquipo = oidEquipo;
        this.descripcion = descripcion;
        this.denominacion = denominacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.montoInvertido = montoInvertido;
    }


    //GETTERS
    public Long getOidEquipo() {
        return oidEquipo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public Timestamp getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public Double getMontoInvertido() {
        return montoInvertido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    //SETTERS
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMontoInvertido(Double montoInvertido) {
        this.montoInvertido = montoInvertido;
    }

    public void setFechaIncorporacion(Timestamp fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setOidEquipo(Long oidEquipo) {
        this.oidEquipo = oidEquipo;
    }
}
