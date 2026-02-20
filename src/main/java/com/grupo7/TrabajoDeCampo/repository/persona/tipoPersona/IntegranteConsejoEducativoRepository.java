package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IntegranteConsejoEducativoRepository extends JpaRepository <IntegranteConsejoEducativo, Long> {
    Optional<IntegranteConsejoEducativo> findByPersona(Persona persona);

    List<IntegranteConsejoEducativo> findByActivoTrue();

    List<IntegranteConsejoEducativo>
    findByPersonaGrupoOidGrupoAndPersonaActivoTrue(Long oidGrupo);

    Optional<IntegranteConsejoEducativo>
    findByOidIntegranteConsejoEducativoAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
            Long oidIntegranteConsejoEducativo,
            Long oidGrupo
    );



}
