package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository <Investigador, Long> {
    Optional<Investigador> findByPersona(Persona persona);

    // listar investigadores activos del grupo
    List<Investigador> findByPersonaGrupoOidGrupoAndPersonaActivoTrue(Long oidGrupo);

    // buscar investigador específico del grupo
    Optional<Investigador> findByOidInvestigadorAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
            Long oidInvestigador,
            Long oidGrupo
    );


}

