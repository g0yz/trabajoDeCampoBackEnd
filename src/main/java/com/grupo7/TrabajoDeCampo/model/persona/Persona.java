package com.grupo7.TrabajoDeCampo.model.persona;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.*;
import jakarta.persistence.*;

@Entity
@Table(name="Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidPersona")
    private Long oidPersona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "horasSemanales")
    private Integer horasSemanales;

    @Enumerated(EnumType.STRING)
    @Column(name="tipoPersona",nullable = false)
    private TipoPersona tipoPersona;

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name="oidUsuario",referencedColumnName = "oidUsuario",nullable = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="oidGrupo", referencedColumnName = "oidGrupo", nullable = false)
    private Grupo grupo;


    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private Personal personal;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private Investigador investigador;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private Becario becario;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private IntegranteConsejoEducativo integranteConsejoEducativo;



    //CONSTRUCTORES

    public Persona() {
    }

    public Persona(String nombre, String apellido, Integer horasSemanales , TipoPersona tipoPersona, Grupo grupo ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.tipoPersona = tipoPersona;
        this.grupo = grupo;
        this.activo = true;
    }


    //GETTERS


    public Long getOidPersona() { return oidPersona; }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public Personal getPersonal() {
        return personal;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public Becario getBecario() {
        return becario;
    }

    public IntegranteConsejoEducativo getIntegranteConsejoEducativo() {
        return integranteConsejoEducativo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public void setBecario(Becario becario) {
        this.becario = becario;
    }

    public void setIntegranteConsejoEducativo(IntegranteConsejoEducativo integranteConsejoEducativo) {
        this.integranteConsejoEducativo = integranteConsejoEducativo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
