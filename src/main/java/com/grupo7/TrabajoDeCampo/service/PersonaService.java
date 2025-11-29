package com.grupo7.TrabajoDeCampo.service;


import com.grupo7.TrabajoDeCampo.model.Grupo;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.GrupoRepository;
import com.grupo7.TrabajoDeCampo.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final GrupoRepository grupoRepository;

    public PersonaService(PersonaRepository personaRepository, GrupoRepository grupoRepository) {
        this.personaRepository = personaRepository;
        this.grupoRepository = grupoRepository;
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id){
        return personaRepository.findById(id);
    }

    public Persona crearPersona(Persona persona, Long idGrupo) {
        Grupo grupo = grupoRepository.findById(idGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + idGrupo));
        persona.setGrupo(grupo);
        return personaRepository.save(persona);
    }


    public Persona actualizarPersona(Long id, Persona personaActualizada) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));


        if (personaActualizada.getNombre() != null)
            persona.setNombre(personaActualizada.getNombre());

        if (personaActualizada.getApellido() != null)
            persona.setApellido(personaActualizada.getApellido());

        if (personaActualizada.getHorasSemanales() != null)
            persona.setHorasSemanales(personaActualizada.getHorasSemanales());

        return personaRepository.save(persona);
    }


    public void eliminarPersona(Long id){
        personaRepository.deleteById(id);
    }




}
