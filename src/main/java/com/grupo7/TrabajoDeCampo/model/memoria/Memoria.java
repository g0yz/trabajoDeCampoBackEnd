package com.grupo7.TrabajoDeCampo.model.memoria;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
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


    @ManyToOne
    @JoinColumn (name = "oidGrupo", nullable = false)
    private Grupo grupo;

    public Memoria(){}

    public Memoria(Integer anio, Grupo grupo) {
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


    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
