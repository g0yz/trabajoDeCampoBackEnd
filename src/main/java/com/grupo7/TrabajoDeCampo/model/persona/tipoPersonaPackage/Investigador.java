package com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import jakarta.persistence.*;

@Entity
@Table(name = "Investigador")
public class Investigador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidInvestigador")
    private Long oidInvestigador;

    @Column(name = "categoriaUTN")
    private String categoriaUTN;

    @Column(name = "programaDeIncentivos")
    private String programaDeIncentivos;

    @Column(name = "dedicacion")
    private String dedicacion;

    @Column(name = "gradoAcademico")
    private String gradoAcademico;

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name = "oidPersona" , unique = true , nullable = false)
    private Persona persona;


    //CONSTRUCTORES
    public Investigador() {
    }

    public Investigador( Persona persona, String gradoAcademico, String dedicacion, String programaDeIncentivos, String categoriaUTN) {
        this.persona = persona;
        this.gradoAcademico = gradoAcademico;
        this.dedicacion = dedicacion;
        this.programaDeIncentivos = programaDeIncentivos;
        this.categoriaUTN = categoriaUTN;
        this.activo = true;
    }


    //GETTERS
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

    public Persona getPersona() {
        return persona;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    //SETTERS

    public void setCategoriaUTN(String categoriaUTN) {
        this.categoriaUTN = categoriaUTN;
    }

    public void setProgramaDeIncentivos(String programaDeIncentivos) {
        this.programaDeIncentivos = programaDeIncentivos;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
