package com.grupo7.TrabajoDeCampo.repository.persona;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository <Persona, Long> {


    List<Persona> findByActivoTrue();

    Optional<Persona> findByOidPersonaAndGrupoOidGrupoAndActivoTrue(
            Long oidPersona,
            Long oidGrupo
    );

}
