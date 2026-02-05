package com.grupo7.TrabajoDeCampo.model.usuario;
import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oidUsuario")
    private Long oidUsuario;

    @Column(nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name="oidPersona",referencedColumnName = "oidPersona",nullable = true)
    private Persona persona;


    //CONSTRUCTORES
    public Usuario(){
    }

    public Usuario(String email,String password,Role role ){
        this.email = email;
        this.role = role;
        setPassword(password);
    }


    public Usuario(String email,String password,Role role ,Persona persona){
        this.email = email;
        this.role = role;
        this.persona = persona;
        setPassword(password);
    }


    //GETTERS


    public Long getOidUsuario() {
        return oidUsuario;
    }

    public String getEmail(){
        return email;
    }

    public Role getRole() {
       return role;
    }

    public String getPassword(){
        return password;
    }

    public Persona getPersona() {
        return persona;
    }




//SETTERS

    public void setEmail(String email){
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
