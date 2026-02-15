package com.grupo7.TrabajoDeCampo.repository.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository <Persona, Long> {

    Optional<Persona> findByOidPersonaAndGrupoOidGrupoAndActivoTrue(
            Long oidPersona,
            Long oidGrupo
    );

}
