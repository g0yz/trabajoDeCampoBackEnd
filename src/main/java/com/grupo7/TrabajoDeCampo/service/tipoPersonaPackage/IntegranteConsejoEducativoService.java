package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.IntegranteConsejoEducativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegranteConsejoEducativoService {

    private final IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository;

    public IntegranteConsejoEducativoService(IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository){
        this.integranteConsejoEducativoRepository = integranteConsejoEducativoRepository;
    }

    public List<IntegranteConsejoEducativo> listarIntegrantesConsejoEducativo(){ return integranteConsejoEducativoRepository.findAll();}

    public Optional<IntegranteConsejoEducativo> obtenerIntegranteConsejoEducativoPorId(Long oid){return integranteConsejoEducativoRepository.findById(oid);}

    public IntegranteConsejoEducativo crearIntegranteConsejoEducativo (Persona persona){
        IntegranteConsejoEducativo integranteConsejoEducativo = new IntegranteConsejoEducativo();
        integranteConsejoEducativo.setPersona(persona);
        return integranteConsejoEducativoRepository.save(integranteConsejoEducativo);
    }

    public IntegranteConsejoEducativo actualizarIntegranteConsejoEducativo (Long oid, IntegranteConsejoEducativo integranteConsejoEducativoActualizado){
        IntegranteConsejoEducativo integranteConsejoEducativo = integranteConsejoEducativoRepository.findById(oid).orElseThrow(() -> new RuntimeException("IntegranteConsejoEducativo no encontrada con oid: " + oid));

        if (integranteConsejoEducativoActualizado.getCargo() != null)
            integranteConsejoEducativo.setCargo(integranteConsejoEducativoActualizado.getCargo());

        return integranteConsejoEducativoRepository.save(integranteConsejoEducativo);

    }

    public void eliminarIntegranteConsejoEducativo (Long oid){ integranteConsejoEducativoRepository.deleteById(oid);}

}
