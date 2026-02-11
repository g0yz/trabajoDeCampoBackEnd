package com.grupo7.TrabajoDeCampo.model.memoria;


import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MemoriaEquipo")
public class MemoriaEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oidMemoriaEquipo;

    @ManyToOne
    @JoinColumn(name = "oidMemoria", nullable = false)
    private Memoria memoria;

    // ===== DATOS COPIADOS DEL EQUIPO =====

    @Column(nullable = false)
    private Long oidEquipo;

    @Column(nullable = false)
    private String denominacion;

    @Column
    private Timestamp fechaIncorporacion;

    @Column
    private Double montoInvertido;

    @Column
    private String descripcion;




    // ===== CONSTRUCTORES =====

    public MemoriaEquipo() {}

    public MemoriaEquipo(Memoria memoria, Equipo equipo) {
        this.memoria = memoria;
        this.oidEquipo = equipo.getOidEquipo();
        this.denominacion = equipo.getDenominacion();
        this.descripcion = equipo.getDescripcion();
        this.montoInvertido = equipo.getMontoInvertido();
        this.fechaIncorporacion = equipo.getFechaIncorporacion();
    }

    // ===== GETTERS =====

    public Long getOidMemoriaEquipo() {
        return oidMemoriaEquipo;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Long getOidEquipo() {
        return oidEquipo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getMontoInvertido() {
        return montoInvertido;
    }

    public Timestamp getFechaIncorporacion() {
        return fechaIncorporacion;
    }
}
