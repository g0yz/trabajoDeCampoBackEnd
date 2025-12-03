package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;


import com.grupo7.TrabajoDeCampo.model.Becario;
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

    public List<Becario> listarBecarios(){ return becarioRepository.findAll();}

    public Optional<Becario> obtenerBecarioPorId(Long oid){
        return becarioRepository.findById(oid);}

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
