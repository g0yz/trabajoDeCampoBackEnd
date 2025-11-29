package com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackageRepository;

import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository <Personal, Long> {
     Optional<Personal> findByPersona(Persona persona);
}
