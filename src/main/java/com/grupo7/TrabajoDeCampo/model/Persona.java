package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

@Entity
@Table(name="Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="oidPersona")
    private Long idPersona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "horasSemanales")
    private String horasSemanales;

    @Enumerated(EnumType.STRING)
    @Column(name="tipoPersona",nullable = false)
    private TipoPersona tipoPersona;

    @OneToOne
    @JoinColumn(name="oidUsuario",referencedColumnName = "oidUsuario",nullable = true)
    private Usuario Usuario;

    @OneToOne
    @JoinColumn(name="oidGrupo", referencedColumnName = "oidGrupo", nullable = false)
    private Grupo grupo;


    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Personal personal;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Investigador investigador;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Becario becario;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private IntegranteConsejoEducativo integranteConsejoEducativo;



    //CONSTRUCTORES

    public Persona() {
    }

    public Persona(String nombre, String apellido, String horasSemanales , TipoPersona tipoPersona ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.horasSemanales = horasSemanales;
        this.tipoPersona = tipoPersona;
    }

    //GETTERS
    public long getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public String getHorasSemanales() {
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

    //SETTERS
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }

    public void setHorasSemanales(String horasSemanales) {
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
