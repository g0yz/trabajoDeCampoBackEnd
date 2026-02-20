package com.grupo7.TrabajoDeCampo.service.persona.tipoPersona;


import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.BecarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BecarioService {

    private final BecarioRepository becarioRepository;
    private final PersonaRepository personaRepository;


    public BecarioService (BecarioRepository becarioRepository, PersonaRepository personaRepository){
        this.becarioRepository = becarioRepository;
        this.personaRepository = personaRepository;
    }


    public List<BecarioResponse> listarBecarios() {
        return becarioRepository.findByActivoTrue()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public BecarioResponse obtenerBecarioPorId(Long oidBecario) {
        Becario becario = becarioRepository.findById(oidBecario)
                .orElseThrow(() -> new RuntimeException("Becario no encontrado"));

        return mapearAResponse(becario);
    }

    private BecarioResponse mapearAResponse(Becario b) {
        return new BecarioResponse(
                b.getPersona().getOidPersona(),
                b.getOidBecario(),
                b.getTipoBecario(),
                b.getFuenteFinanciamiento(),
                b.getActivo(),

                // Persona
                b.getPersona().getNombre(),
                b.getPersona().getApellido(),
                b.getPersona().getHorasSemanales(),

                // Grupo
                b.getPersona().getGrupo().getOidGrupo(),
                b.getPersona().getGrupo().getNombreGrupo()
        );
    }




    //falta modificar becario crear
    public Becario crearBecario (Becario datosBecario, Long oid) {

        Persona persona = personaRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Becario becario = new Becario();
        becario.setPersona(persona);

        becario.setFuenteFinanciamiento(datosBecario.getFuenteFinanciamiento());
        becario.setTipoBecario(datosBecario.getTipoBecario());

        return becarioRepository.save(becario);
    }

    //falta modificar becario crear

    public Becario actualizarBecario ( Long oid,Becario becarioActualizado){
        Becario becario = becarioRepository.findById(oid).orElseThrow(() -> new RuntimeException("Becario no encontrada con oid: " + oid));

        if (becarioActualizado.getFuenteFinanciamiento() != null)
            becario.setFuenteFinanciamiento(becarioActualizado.getFuenteFinanciamiento());

        if (becarioActualizado.getTipoBecario() != null)
            becario.setTipoBecario(becarioActualizado.getTipoBecario());

        return becarioRepository.save(becario);

    }

    public void eliminarBecario (Long oid){ becarioRepository.deleteById(oid);}


    public List<BecarioResponse> listarBecariosDelGrupo(Long oidGrupo) {

        return becarioRepository
                .findByPersonaGrupoOidGrupoAndPersonaActivoTrue(oidGrupo)
                .stream()
                .map(b -> new BecarioResponse(
                        b.getPersona().getOidPersona(),
                        b.getOidBecario(),
                        b.getTipoBecario(),
                        b.getFuenteFinanciamiento(),
                        b.getPersona().getActivo(),
                        b.getPersona().getNombre(),
                        b.getPersona().getApellido(),
                        b.getPersona().getHorasSemanales(),
                        b.getPersona().getGrupo().getOidGrupo(),
                        b.getPersona().getGrupo().getNombreGrupo()
                ))
                .toList();
    }



    public BecarioResponse obtenerBecarioDelGrupo(
            Long oidGrupo,
            Long oidBecario
    ) {
        Becario b = becarioRepository
                .findByOidBecarioAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
                        oidBecario,
                        oidGrupo
                )
                .orElseThrow(() ->
                        new RuntimeException("Becario no encontrado en el grupo")
                );

        return new BecarioResponse(
                b.getPersona().getOidPersona(),
                b.getOidBecario(),
                b.getTipoBecario(),
                b.getFuenteFinanciamiento(),
                b.getPersona().getActivo(),
                b.getPersona().getNombre(),
                b.getPersona().getApellido(),
                b.getPersona().getHorasSemanales(),
                b.getPersona().getGrupo().getOidGrupo(),
                b.getPersona().getGrupo().getNombreGrupo()
        );
    }





}
