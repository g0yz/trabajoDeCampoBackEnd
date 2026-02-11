package com.grupo7.TrabajoDeCampo.model.memoria;

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

    @Column(nullable = false)
    private Long oidPersona;

    @Column
    private String nombre;
    @Column
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPersona tipoPersona;

    @Column
    private Integer horasSemanales;

    @Column(nullable = true)
    private String categoriaUTN;
    @Column(nullable = true)
    private String programaDeIncentivos;
    @Column(nullable = true)
    private String dedicacion;
    @Column(nullable = true)
    private String gradoAcademico;
    @Column(nullable = true)
    private String tipoBecario;
    @Column(nullable = true)
    private String tipoPersonal;
    @Column(nullable = true)
    private String cargo;
    @Column(nullable = true)
    private String fuenteDeFinanciamiento;

    public MemoriaPersona() {}

    public MemoriaPersona(
            Memoria memoria,
            Long oidPersona,
            String nombre,
            String apellido,
            TipoPersona tipoPersona,
            Integer horasSemanales,
            String programaDeIncentivos,
            String categoriaUTN,
            String dedicacion,
            String gradoAcademico,
            String tipoBecario,
            String tipoPersonal,
            String cargo,
            String fuenteDeFinanciamiento
    ) {
        this.memoria = memoria;
        this.oidPersona = oidPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoPersona = tipoPersona;
        this.horasSemanales = horasSemanales;
        this.programaDeIncentivos = programaDeIncentivos;
        this.categoriaUTN = categoriaUTN;
        this.dedicacion = dedicacion;
        this.gradoAcademico = gradoAcademico;
        this.tipoBecario = tipoBecario;
        this.tipoPersonal = tipoPersonal;
        this.cargo = cargo;
        this.fuenteDeFinanciamiento = fuenteDeFinanciamiento;
    }

    public Long getOidMemoriaPersona() {
        return oidMemoriaPersona;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Long getOidPersona() {
        return oidPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public String getCategoriaUTN() {
        return categoriaUTN;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public String getTipoBecario() {
        return tipoBecario;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public String getCargo() {
        return cargo;
    }

    public String getProgramaDeIncentivos() {
        return programaDeIncentivos;
    }

    public void setOidMemoriaPersona(Long oidMemoriaPersona) {
        this.oidMemoriaPersona = oidMemoriaPersona;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public void setOidPersona(Long oidPersona) {
        this.oidPersona = oidPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public void setCategoriaUTN(String categoriaUTN) {
        this.categoriaUTN = categoriaUTN;
    }

    public void setProgramaDeIncentivos(String programaDeIncentivos) {
        this.programaDeIncentivos = programaDeIncentivos;
    }

    public String getFuenteDeFinanciamiento() {
        return fuenteDeFinanciamiento;
    }

    public void setFuenteDeFinanciamiento(String fuenteDeFinanciamiento) {
        this.fuenteDeFinanciamiento = fuenteDeFinanciamiento;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public void setTipoBecario(String tipoBecario) {
        this.tipoBecario = tipoBecario;
    }

    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
