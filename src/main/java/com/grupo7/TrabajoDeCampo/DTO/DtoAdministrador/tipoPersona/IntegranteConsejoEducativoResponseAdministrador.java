package com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.tipoPersona;

public class IntegranteConsejoEducativoResponseAdministrador {

    private Long oidIntegranteConsejoEducativo;
    private String cargo;
    private Boolean activo;

    private String nombre;
    private String apellido;
    private Integer horasSemanales;

    private Long oidGrupo;
    private String nombreGrupo;

    public IntegranteConsejoEducativoResponseAdministrador(
            Long oidIntegranteConsejoEducativo,
            String cargo,
            Boolean activo,
            String nombre,
            String apellido,
            Integer horasSemanales,
            Long oidGrupo,
            String nombreGrupo) {

        this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
        this.cargo = cargo;
        this.activo = activo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.oidGrupo = oidGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getOidIntegranteConsejoEducativo() { return oidIntegranteConsejoEducativo; }
    public String getCargo() { return cargo; }
    public Boolean getActivo() { return activo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getHorasSemanales() { return horasSemanales; }
    public Long getOidGrupo() { return oidGrupo; }
    public String getNombreGrupo() { return nombreGrupo; }
}