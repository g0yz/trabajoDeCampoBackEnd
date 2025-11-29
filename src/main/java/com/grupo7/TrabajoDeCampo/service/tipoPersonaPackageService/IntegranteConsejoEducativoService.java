package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService;

import com.grupo7.TrabajoDeCampo.model.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackageRepository.IntegranteConsejoEducativoRepository;
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

    public Optional<IntegranteConsejoEducativo> obtenerIntegranteConsejoEducativoPorId(Long id){return integranteConsejoEducativoRepository.findById(id);}

    public IntegranteConsejoEducativo crearintegranteConsejoEducativo (Persona persona){
        IntegranteConsejoEducativo integranteConsejoEducativo = new IntegranteConsejoEducativo();
        integranteConsejoEducativo.setPersona(persona);
        return integranteConsejoEducativoRepository.save(integranteConsejoEducativo);
    }

    public IntegranteConsejoEducativo integranteConsejoEducativo (Long id, IntegranteConsejoEducativo integranteConsejoEducativoActualizado){
        IntegranteConsejoEducativo integranteConsejoEducativo = integranteConsejoEducativoRepository.findById(id).orElseThrow(() -> new RuntimeException("IntegranteConsejoEducativo no encontrada con id: " + id));

        if (integranteConsejoEducativoActualizado.getCargo() != null)
            integranteConsejoEducativo.setCargo(integranteConsejoEducativoActualizado.getCargo());

        return integranteConsejoEducativoRepository.save(integranteConsejoEducativo);

    }

    public void eliminarIntegranteConsejoEducativo (Long id){ integranteConsejoEducativoRepository.deleteById(id);}

}
