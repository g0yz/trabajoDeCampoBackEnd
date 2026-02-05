package com.grupo7.TrabajoDeCampo.model.memoria;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import jakarta.persistence.*;

@Entity
@Table(name = "MemoriaPersona")
public class MemoriaPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oidMemoriaPersona;

    @ManyToOne
    @JoinColumn(name = "oidMemoria", nullable = false)
    private Memoria memoria;

    @ManyToOne
    @JoinColumn(name = "oidPersona", nullable = false)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPersona tipoPersona;


    @Column(name = "horasSemanales")
    private Integer horasSemanales;

    public MemoriaPersona(){}

    public MemoriaPersona(Memoria memoria, Persona persona, TipoPersona tipoPersona, Integer horasSemanales) {
        this.memoria = memoria;
        this.persona = persona;
        this.tipoPersona = tipoPersona;
        this.horasSemanales = horasSemanales;
    }

    public Long getOidMemoriaPersona() {
        return oidMemoriaPersona;
    }


    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }
}
