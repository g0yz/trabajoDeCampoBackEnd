package com.grupo7.TrabajoDeCampo.model;
import jakarta.persistence.*;

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

    @Column(name = "organigrama", columnDefinition = "LONGBLOB")
    private byte[] organigrama;

    @Column(name = "objetivoYDesarollo")
    private String objetivoYDesarollo;


    //CONSTRUCTORES
    public Grupo() {
    }

    public Grupo(String nombreGrupo, String sigla, String email, byte[] organigrama, String objetivoYDesarollo) {
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

    public byte[] getOrganigrama() {
        return organigrama;
    }

    public String getObjetivoYDesarollo() {
        return objetivoYDesarollo;
    }

    //SETTERS
    public void setObjetivoYDesarollo(String objetivoYDesarollo) {
        this.objetivoYDesarollo = objetivoYDesarollo;
    }

    public void setOrganigrama(byte[] organigrama) {
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
}
