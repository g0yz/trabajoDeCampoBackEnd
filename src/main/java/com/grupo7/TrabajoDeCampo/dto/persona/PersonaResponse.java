package com.grupo7.TrabajoDeCampo.dto.persona;

import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoBecario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoPersonal;

public class PersonaResponse {

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
    private TipoBecario tipoBecario;

    // PERSONAL
    private TipoPersonal tipoPersonal;

    // CONSEJO EDUCATVO
    private String cargo;

    //GRUPO
    private Long oidGrupo;
    private String nombreGrupo;


    public PersonaResponse(Long oidPersona,String nombre, String apellido, Integer horasSemanales, TipoPersona tipoPersona, String categoriaUTN, String programaDeIncentivos, String dedicacion, String gradoAcademico, String fuenteFinanciamiento, TipoBecario tipoBecario, TipoPersonal tipoPersonal, String cargo, Long oidGrupo, String nombreGrupo) {
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
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }


    public Long getOidPersona() {
        return oidPersona;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getCategoriaUTN() {
        return categoriaUTN;
    }

    public void setCategoriaUTN(String categoriaUTN) {
        this.categoriaUTN = categoriaUTN;
    }

    public String getProgramaDeIncentivos() {
        return programaDeIncentivos;
    }

    public void setProgramaDeIncentivos(String programaDeIncentivos) {
        this.programaDeIncentivos = programaDeIncentivos;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public TipoBecario getTipoBecario() {
        return tipoBecario;
    }

    public void setTipoBecario(TipoBecario tipoBecario) {
        this.tipoBecario = tipoBecario;
    }

    public TipoPersonal getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(TipoPersonal tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getOidGrupo() {
        return oidGrupo;
    }

    public void setOidGrupo(Long oidGrupo) {
        this.oidGrupo = oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
}
