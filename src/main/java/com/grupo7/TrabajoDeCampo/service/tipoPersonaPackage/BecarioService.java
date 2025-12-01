package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;


import com.grupo7.TrabajoDeCampo.model.Becario;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.BecarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BecarioService {

    private final BecarioRepository becarioRepository;


    public BecarioService (BecarioRepository becarioRepository){
        this.becarioRepository = becarioRepository;
    }

    public List<Becario> listarBecarios(){ return becarioRepository.findAll();}

    public Optional<Becario> obtenerBecarioPorId(Long id){return becarioRepository.findById(id);}

    public Becario crearBecario (Persona persona){
        Becario becario = new Becario();
        becario.setPersona(persona);
        persona.setBecario(becario);

        return becarioRepository.save(becario);
    }

    public Becario actualizarBecario (Long id, Becario becarioActualizado){
        Becario becario = becarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Becario no encontrada con id: " + id));

        if (becarioActualizado.getFuenteFinanciamiento() != null)
            becario.setFuenteFinanciamiento(becarioActualizado.getFuenteFinanciamiento());

        if (becarioActualizado.getTipoBecario() != null)
            becario.setTipoBecario(becarioActualizado.getTipoBecario());

        return becarioRepository.save(becario);

    }

    public void eliminarBecario (Long id){ becarioRepository.deleteById(id);}


}
