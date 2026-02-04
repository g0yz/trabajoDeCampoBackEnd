package com.grupo7.TrabajoDeCampo.controller;
import com.grupo7.TrabajoDeCampo.DTO.UsuarioRegisterRequest;
import com.grupo7.TrabajoDeCampo.DTO.UsuarioLoginRequest;
import com.grupo7.TrabajoDeCampo.handler.JwtService;
import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.grupo7.TrabajoDeCampo.repository.UsuarioRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Registro de usuario
    @PostMapping("/register")
    public String register(@RequestBody UsuarioRegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return "El correo ya está registrado.";
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole(Role.Administrador); // IMPORTANTE este metodo crea un usuario sin persona y registra al usuario como admin

        usuarioRepository.save(usuario);

        return "Usuario registrado con éxito";
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", usuario.getRole().name()
        ));
    }


}
