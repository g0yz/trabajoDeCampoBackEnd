package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BecarioRepository extends  JpaRepository <Becario, Long>{
    Optional<Becario> findByPersona(Persona persona);

    List<Becario> findByPersonaGrupoOidGrupoAndPersonaActivoTrue(Long oidGrupo);

    List<Becario> findByActivoTrue();

    Optional<Becario> findByOidBecarioAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
            Long oidBecario,
            Long oidGrupo
    );



}

