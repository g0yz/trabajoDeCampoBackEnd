package com.grupo7.TrabajoDeCampo.service.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.tipoPersona.IntegranteConsejoEducativoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.persona.IntegranteConsejoEducativoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.IntegranteConsejoEducativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteConsejoEducativoService {

    private final IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository;

    public IntegranteConsejoEducativoService(IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository){
        this.integranteConsejoEducativoRepository = integranteConsejoEducativoRepository;
    }


    public List<IntegranteConsejoEducativoResponseAdministrador> listarIntegrantesConsejoEducativo() {
        return integranteConsejoEducativoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public IntegranteConsejoEducativoResponseAdministrador obtenerIntegranteConsejoEducativoPorId(
            Long oidIntegranteConsejoEducativo) {

        IntegranteConsejoEducativo integrante =
                integranteConsejoEducativoRepository.findById(oidIntegranteConsejoEducativo)
                        .orElseThrow(() -> new RuntimeException(
                                "Integrante del Consejo Educativo no encontrado"));

        return mapearAResponse(integrante);
    }

    private IntegranteConsejoEducativoResponseAdministrador mapearAResponse(
            IntegranteConsejoEducativo i) {

        return new IntegranteConsejoEducativoResponseAdministrador(
                i.getOidIntegranteConsejoEducativo(),
                i.getCargo(),
                i.getActivo(),

                // Persona
                i.getPersona().getNombre(),
                i.getPersona().getApellido(),
                i.getPersona().getHorasSemanales(),

                // Grupo
                i.getPersona().getGrupo().getOidGrupo(),
                i.getPersona().getGrupo().getNombreGrupo()
        );
    }

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

    public List<IntegranteConsejoEducativoResponseIntegrante>
    listarIntegrantesConsejoEducativoDelGrupo(Long oidGrupo) {

        return integranteConsejoEducativoRepository
                .findByPersonaGrupoOidGrupoAndPersonaActivoTrue(oidGrupo)
                .stream()
                .map(i -> new IntegranteConsejoEducativoResponseIntegrante(
                        i.getOidIntegranteConsejoEducativo(),
                        i.getCargo(),
                        i.getActivo(),
                        i.getPersona().getNombre(),
                        i.getPersona().getApellido(),
                        i.getPersona().getHorasSemanales()
                ))
                .toList();
    }


    public IntegranteConsejoEducativoResponseIntegrante obtenerIntegranteConsejoEducativoDelGrupo(
            Long oidGrupo,
            Long oidIntegranteConsejoEducativo) {

        IntegranteConsejoEducativo integrante =
                integranteConsejoEducativoRepository
                        .findByOidIntegranteConsejoEducativoAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
                                oidIntegranteConsejoEducativo, oidGrupo
                        )
                        .orElseThrow(() -> new RuntimeException("Integrante no encontrado"));

        return new IntegranteConsejoEducativoResponseIntegrante(
                integrante.getOidIntegranteConsejoEducativo(),
                integrante.getCargo(),
                integrante.getActivo(),
                integrante.getPersona().getNombre(),
                integrante.getPersona().getApellido(),
                integrante.getPersona().getHorasSemanales()
        );
    }






}
