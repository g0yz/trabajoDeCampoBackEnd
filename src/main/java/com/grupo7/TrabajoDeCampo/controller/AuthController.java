package com.grupo7.TrabajoDeCampo.controller;
import com.grupo7.TrabajoDeCampo.DTO.UsuarioRegisterRequest;
import com.grupo7.TrabajoDeCampo.DTO.UsuarioLoginRequest;
import com.grupo7.TrabajoDeCampo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.grupo7.TrabajoDeCampo.repository.UsuarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Registro de usuario
    @PostMapping("/register")
    public String register(@RequestBody UsuarioRegisterRequest request) {  //uso de UsuarioRegisterRequest
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return "El correo ya está registrado.";
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // encripta la contraseña

        usuarioRepository.save(usuario);

        return "Usuario registrado con éxito";
    }

    // Login de usuario
    @PostMapping("/login")
    public String login(@RequestBody UsuarioLoginRequest request) {  //Uso de UsuarioLoginRequest
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return "Usuario no encontrado.";
        }

        Usuario usuario = userOpt.get();

        if (passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {

            return "Login exitoso: " + usuario.getEmail();
        } else {
            return "Contraseña incorrecta.";
        }
    }


}
