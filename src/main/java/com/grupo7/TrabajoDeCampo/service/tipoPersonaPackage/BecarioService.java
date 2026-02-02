package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;


import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.BecarioResponse;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.Becario;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.BecarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BecarioService {

    private final BecarioRepository becarioRepository;
    private final PersonaRepository personaRepository;


    public BecarioService (BecarioRepository becarioRepository, PersonaRepository personaRepository){
        this.becarioRepository = becarioRepository;
        this.personaRepository = personaRepository;
    }


    public List<BecarioResponse> listarBecarios() {
        return becarioRepository.findAll()
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




    public Becario crearBecario (Becario datosBecario, Long oid) {

        Persona persona = personaRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Becario becario = new Becario();
        becario.setPersona(persona);

        becario.setFuenteFinanciamiento(datosBecario.getFuenteFinanciamiento());
        becario.setTipoBecario(datosBecario.getTipoBecario());

        return becarioRepository.save(becario);
    }

    public Becario actualizarBecario ( Long oid,Becario becarioActualizado){
        Becario becario = becarioRepository.findById(oid).orElseThrow(() -> new RuntimeException("Becario no encontrada con oid: " + oid));

        if (becarioActualizado.getFuenteFinanciamiento() != null)
            becario.setFuenteFinanciamiento(becarioActualizado.getFuenteFinanciamiento());

        if (becarioActualizado.getTipoBecario() != null)
            becario.setTipoBecario(becarioActualizado.getTipoBecario());

        return becarioRepository.save(becario);

    }

    public void eliminarBecario (Long oid){ becarioRepository.deleteById(oid);}


}
