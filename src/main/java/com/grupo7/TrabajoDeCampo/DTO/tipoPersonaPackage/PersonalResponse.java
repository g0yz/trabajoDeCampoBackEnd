package com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.TipoPersonal;

public class PersonalResponse {

    private Long oidPersonal;
    private TipoPersonal tipoPersonal;
    private Boolean activo;

    private String nombre;
    private String apellido;
    private Integer horasSemanales;

    private Long oidGrupo;
    private String nombreGrupo;

    public PersonalResponse(
            Long oidPersonal,
            TipoPersonal tipoPersonal,
            Boolean activo,
            String nombre,
            String apellido,
            Integer horasSemanales,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidPersonal = oidPersonal;
        this.tipoPersonal = tipoPersonal;
        this.activo = activo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidPersonal() { return oidPersonal; }
    public TipoPersonal getTipoPersonal() { return tipoPersonal; }
    public Boolean getActivo() { return activo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getHorasSemanales() { return horasSemanales; }
    public Long getOidGrupo() { return oidGrupo; }
    public String getNombreGrupo() { return nombreGrupo; }
}