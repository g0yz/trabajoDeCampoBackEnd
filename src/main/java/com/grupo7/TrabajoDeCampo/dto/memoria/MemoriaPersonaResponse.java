package com.grupo7.TrabajoDeCampo.dto.memoria;

import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoBecario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoPersonal;

public class MemoriaPersonaResponse {

    private Long oidPersona;
    private String nombre;
    private String apellido;
    private Integer horasSemanales;
    private TipoPersona tipoPersona;

    // INVESTIGADOR
    private String categoriaUTN;
    private String programaDeIncentivos;
    private String dedicacion;
    private String gradoAcademico;

    // BECARIO
    private String fuenteFinanciamiento;
    private String tipoBecario;

    // PERSONAL
    private String tipoPersonal;

    // CONSEJO EDUCATVO
    private String cargo;


    public MemoriaPersonaResponse(Long oidPersona,String nombre, String apellido, Integer horasSemanales, TipoPersona tipoPersona, String categoriaUTN, String programaDeIncentivos, String dedicacion, String gradoAcademico, String fuenteFinanciamiento, String tipoBecario, String tipoPersonal , String cargo) {
        this.oidPersona = oidPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.tipoPersona = tipoPersona;
        this.categoriaUTN = categoriaUTN;
        this.programaDeIncentivos = programaDeIncentivos;
        this.dedicacion = dedicacion;
        this.gradoAcademico = gradoAcademico;
        this.fuenteFinanciamiento = fuenteFinanciamiento;
        this.tipoBecario = tipoBecario;
        this.tipoPersonal = tipoPersonal;
        this.cargo = cargo;
    }

    public Long getOidPersona() { return oidPersona;}

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public String getCategoriaUTN() {
        return categoriaUTN;
    }

    public String getProgramaDeIncentivos() {
        return programaDeIncentivos;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
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

}
