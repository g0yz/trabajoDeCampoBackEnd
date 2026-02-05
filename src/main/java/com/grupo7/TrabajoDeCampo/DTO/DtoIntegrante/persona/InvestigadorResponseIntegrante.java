package com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona;

public class InvestigadorResponseIntegrante {


    private Long oidInvestigador;
    private String categoriaUTN;
    private String programaDeIncentivos;
    private String dedicacion;
    private String gradoAcademico;
    private Boolean activo;

    // Persona
    private String nombre;
    private String apellido;
    private Integer horasSemanales;


    public InvestigadorResponseIntegrante(
            Long oidInvestigador,
            String categoriaUTN,
            String programaDeIncentivos,
            String dedicacion,
            String gradoAcademico,
            Boolean activo,
            String nombre,
            String apellido,
            Integer horasSemanales
    ) {
        this.oidInvestigador = oidInvestigador;
        this.categoriaUTN = categoriaUTN;
        this.programaDeIncentivos = programaDeIncentivos;
        this.dedicacion = dedicacion;
        this.gradoAcademico = gradoAcademico;
        this.activo = activo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
    }

    public Long getOidInvestigador() {
        return oidInvestigador;
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

}


