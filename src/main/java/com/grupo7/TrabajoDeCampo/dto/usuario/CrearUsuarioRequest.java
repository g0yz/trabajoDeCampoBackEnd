package com.grupo7.TrabajoDeCampo.dto.usuario;

import com.grupo7.TrabajoDeCampo.handler.Role;

public class CrearUsuarioRequest {

    private Long oidPersona;
    private String email;
    private String password;
    private Role role;


    public CrearUsuarioRequest(Long oidPersona, String email, String password, Role role) {
        this.oidPersona = oidPersona;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getOidPersona() {
        return oidPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
