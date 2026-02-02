package com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntegranteConsejoEducativoRepository extends JpaRepository <IntegranteConsejoEducativo, Long> {
    Optional<IntegranteConsejoEducativo> findByPersona(Persona persona);

}
