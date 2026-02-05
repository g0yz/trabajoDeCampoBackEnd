package com.grupo7.TrabajoDeCampo.repository.usuario;

import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

