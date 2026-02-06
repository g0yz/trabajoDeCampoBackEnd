package com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.tipoPersona;


import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.TipoBecario;

public class BecarioResponseAdministrador {

    private Long oidBecario;
    private TipoBecario tipoBecario;
    private String fuenteFinanciamiento;
    private Boolean activo;

    // Persona
    private String nombre;
    private String apellido;
    private Integer horasSemanales;

    // Grupo
    private Long oidGrupo;
    private String nombreGrupo;

    public BecarioResponseAdministrador(
            Long oidBecario,
            TipoBecario tipoBecario,
            String fuenteFinanciamiento,
            Boolean activo,
            String nombre,
            String apellido,
            Integer horasSemanales,
            Long oidGrupo,
            String nombreGrupo
    ) {
        this.oidBecario = oidBecario;
        this.tipoBecario = tipoBecario;
        this.fuenteFinanciamiento = fuenteFinanciamiento;
        this.activo = activo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    // getters


    public Long getOidBecario() {
        return oidBecario;
    }

    public TipoBecario getTipoBecario() {
        return tipoBecario;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public Boolean getActivo() {
        return activo;
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

    public Long getOidGrupo() {
        return oidGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }
}