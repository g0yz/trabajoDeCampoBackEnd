package com.grupo7.TrabajoDeCampo.model;
import com.grupo7.TrabajoDeCampo.handler.Role;
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

  // @Column(nullable = false)
    //private Role role;

    @Column(nullable = false)
    private String password;

    //CONSTRUCTORES
    public Usuario(){
    }

    public Usuario(String email,String password ){
        this.email = email;
       // this.role = role;
        setPassword(password);
    }

    //GETTERS


    public Long getOidUsuario() {
        return oidUsuario;
    }

    public String getEmail(){
        return email;
    }

   // public Role getRole() {
     //  return role;
    //}

    public String getPassword(){
        return password;
    }

    //SETTERS
    public void setOidUsuario(Long oidUsuario) {
        this.oidUsuario = oidUsuario;
    }

    public void setEmail(String email){
        this.email = email;
    }

   // public void setRole(Role role) {
      //  this.role = role;
    //}

    public void setPassword(String password) {
        this.password = password;
    }

}
