package com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.tipoPersona;

public class PersonaResponseAdministrador {

    private Long oidPersona;
    private String nombre;
    private String apellido;
    private Integer horasSemanales;
    private String tipoPersona;
    private Boolean activo;

    // grupo mínimo
    private Long oidGrupo;
    private String nombreGrupo;

    public PersonaResponseAdministrador(
            Long oidPersona,
            String nombre,
            String apellido,
            Integer horasSemanales,
            String tipoPersona,
            Boolean activo,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidPersona = oidPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.tipoPersona = tipoPersona;
        this.activo = activo;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidPersona() { return oidPersona; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getHorasSemanales() { return horasSemanales; }
    public String getTipoPersona() { return tipoPersona; }
    public Boolean getActivo() { return activo; }
    public Long getOidGrupo() { return oidGrupo; }
    public String getNombreGrupo() { return nombreGrupo; }
}
