package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oidGrupo")
    private Long oidGrupo;

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

    @OneToMany(mappedBy = "grupo")
    private List<Persona> personas;

    @OneToMany(mappedBy = "grupo")
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "grupo")
    private List<Documento> documentos;

    //CONSTRUCTORES
    public Grupo() {
    }

    public Grupo(String nombreGrupo, String sigla, String email, String organigrama, String objetivoYDesarollo) {
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

    public List<Persona> getPersonas() {
        return personas;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    //SETTERS
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

    public void setOidGrupo(Long oidGrupo) {
        this.oidGrupo = oidGrupo;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

}
