package com.grupo7.TrabajoDeCampo.repository.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;

public interface PersonaRepository extends JpaRepository <Persona, Long> {

}
