package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonalRepository extends JpaRepository <Personal, Long> {
     Optional<Personal> findByPersona(Persona persona);

     List<Personal> findByPersonaGrupoOidGrupoAndPersonaActivoTrue(Long oidGrupo);

     Optional<Personal> findByOidPersonalAndPersonaGrupoOidGrupoAndPersonaActivoTrue(Long oidPersonal,Long oidGrupo);
}
