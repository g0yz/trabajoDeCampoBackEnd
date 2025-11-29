package com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackageRepository;

import com.grupo7.TrabajoDeCampo.model.Becario;
import com.grupo7.TrabajoDeCampo.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BecarioRepository extends  JpaRepository <Becario, Long>{
    Optional<Becario> findByPersona(Persona persona);
}

