package com.grupo7.TrabajoDeCampo.service.usuario;


import com.grupo7.TrabajoDeCampo.dto.usuario.CrearUsuarioRequest;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository , PersonaRepository personaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Usuario crearUsuarioParaPersona(CrearUsuarioRequest dto) {

        Persona persona = personaRepository.findById(dto.getOidPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRole(dto.getRole());
        usuario.setPersona(persona);

        return usuarioRepository.save(usuario);
    }
}
