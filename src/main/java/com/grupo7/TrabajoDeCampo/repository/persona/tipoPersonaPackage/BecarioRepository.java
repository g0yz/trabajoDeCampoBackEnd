package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BecarioRepository extends  JpaRepository <Becario, Long>{
    Optional<Becario> findByPersona(Persona persona);
}

