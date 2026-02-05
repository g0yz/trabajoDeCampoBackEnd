package com.grupo7.TrabajoDeCampo.model.grupo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidGrupo")
    private Long oidGrupo;

    @Column(name = "facultadRegional")
    private String facultadRegional;


    @Column(name = "nombreGrupo")
    private String nombreGrupo;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "email")
    private String email;

    @Column(name = "organigrama")
    private String organigrama;

    @Column(name = "objetivoYDesarollo")
    private String objetivoYDesarollo;


    //CONSTRUCTORES
    public Grupo() {
    }

    public Grupo(String facultadRegional,String nombreGrupo, String sigla, String email, String organigrama, String objetivoYDesarollo) {
        this.facultadRegional = facultadRegional;
        this.nombreGrupo = nombreGrupo;
        this.sigla = sigla;
        this.email = email;
        this.organigrama = organigrama;
        this.objetivoYDesarollo = objetivoYDesarollo;

    }

    //GETTERS
    public long getOidGrupo() {
        return oidGrupo;
    }

    public String getFacultadRegional() {
        return facultadRegional;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public String getSigla() {
        return sigla;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganigrama() {
        return organigrama;
    }

    public String getObjetivoYDesarollo() {
        return objetivoYDesarollo;
    }



    //SETTERS


    public void setFacultadRegional(String facultadRegional) {
        this.facultadRegional = facultadRegional;
    }

    public void setObjetivoYDesarollo(String objetivoYDesarollo) {
        this.objetivoYDesarollo = objetivoYDesarollo;
    }

    public void setOrganigrama(String organigrama) {
        this.organigrama = organigrama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }



}
