package com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.memoria;

import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoBecario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoPersonal;

public class MemoriaPersonaResponseIntegrante {

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
    private TipoBecario tipoBecario;

    // PERSONAL
    private TipoPersonal tipoPersonal;

    // CONSEJO EDUCATVO
    private String Cargo;


    public MemoriaPersonaResponseIntegrante(String nombre, String apellido, Integer horasSemanales, TipoPersona tipoPersona, String categoriaUTN, String programaDeIncentivos, String dedicacion, String gradoAcademico, String fuenteFinanciamiento, TipoBecario tipoBecario, TipoPersonal tipoPersonal , String cargo) {
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
        this.Cargo = cargo;
    }

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

    public TipoBecario getTipoBecario() {
        return tipoBecario;
    }

    public TipoPersonal getTipoPersonal() {
        return tipoPersonal;
    }

    public String getCargo() {
        return Cargo;
    }
}
