package com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository <Investigador, Long> {
    Optional<Investigador> findByPersona(Persona persona);
}

