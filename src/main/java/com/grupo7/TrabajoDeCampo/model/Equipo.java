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

    @Column(name = "activo")
    private Boolean activo = true;

    //CONSTRUCTORES
    public Equipo() {
    }

    public Equipo(String denominacion, Timestamp fechaIncorporacion, Double montoInvertido, String descripcion, Grupo grupo) {
        this.denominacion = denominacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.montoInvertido = montoInvertido;
        this.descripcion = descripcion;
        this.grupo = grupo;
        this.activo = true;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

}
