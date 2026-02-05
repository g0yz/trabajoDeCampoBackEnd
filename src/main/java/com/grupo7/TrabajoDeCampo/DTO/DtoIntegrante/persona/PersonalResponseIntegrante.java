package com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.TipoPersonal;

public class PersonalResponseIntegrante {


    private Long oidPersonal;
    private TipoPersonal tipoPersonal;
    private Boolean activo;

    private String nombre;
    private String apellido;
    private Integer horasSemanales;


    public PersonalResponseIntegrante(
            Long oidPersonal,
            TipoPersonal tipoPersonal,
            Boolean activo,
            String nombre,
            String apellido,
            Integer horasSemanales) {

        this.oidPersonal = oidPersonal;
        this.tipoPersonal = tipoPersonal;
        this.activo = activo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
    }

    public Long getOidPersonal() { return oidPersonal; }
    public TipoPersonal getTipoPersonal() { return tipoPersonal; }
    public Boolean getActivo() { return activo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getHorasSemanales() { return horasSemanales; }

}
