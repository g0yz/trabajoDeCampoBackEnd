package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "Memoria")
public class Memoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "oidMemoria")
    private Long oidMemoria;

    @Column (name = "anio" , nullable = false)
    private Integer anio;

    @Column( name = "fechaCreacion", nullable = false)
    private Timestamp fechaCreacion;

    @ManyToOne
    @JoinColumn (name = "oidGrupo", nullable = false)
    private Grupo grupo;

    public Memoria(){}

    public Memoria(Timestamp fechaCreacion, Integer anio, Grupo grupo) {
        this.fechaCreacion = fechaCreacion;
        this.anio = anio;
        this.grupo = grupo;
    }


    public Long getOidMemoria() { return oidMemoria; }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
