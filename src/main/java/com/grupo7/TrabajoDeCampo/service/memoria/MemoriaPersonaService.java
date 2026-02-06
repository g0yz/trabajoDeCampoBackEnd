package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaPersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoriaPersonaService {

    private final MemoriaPersonaRepository memoriaPersonaRepository;
    private final MemoriaRepository memoriaRepository;
    private final PersonaRepository personaRepository;

    public MemoriaPersonaService(
            MemoriaPersonaRepository memoriaPersonaRepository,
            MemoriaRepository memoriaRepository,
            PersonaRepository personaRepository) {
        this.memoriaPersonaRepository = memoriaPersonaRepository;
        this.memoriaRepository = memoriaRepository;
        this.personaRepository = personaRepository;
    }



    //ADMINISTRADOR
    // agregar persona a memoria
    public MemoriaPersona agregarPersonaAMemoriaAdmin(
            Long oidMemoria,
            Long oidPersona,
            TipoPersona tipoPersona,
            Integer horasSemanales) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Persona persona = personaRepository.findById(oidPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        memoriaPersonaRepository.findByMemoriaAndPersona(memoria, persona)
                .ifPresent(mp -> {
                    throw new RuntimeException("La persona ya está asociada a la memoria");
                });

        MemoriaPersona mp = new MemoriaPersona(
                memoria,
                persona,
                tipoPersona,
                horasSemanales
        );

        return memoriaPersonaRepository.save(mp);
    }

    // listar personas de una memoria
    public List<MemoriaPersona> listarPersonaPorMemoriaAdmin(Long oidMemoria) {
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaPersonaRepository.findByMemoria(memoria);
    }


    // quitar persona de una memoria
    public void quitarPersonaAMemoriaAdmin(Long oidMemoria, Long oidPersona) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Persona persona = personaRepository.findById(oidPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        MemoriaPersona mp = memoriaPersonaRepository
                .findByMemoriaAndPersona(memoria, persona)
                .orElseThrow(() ->
                        new RuntimeException("La persona no está asociada a la memoria"));

        memoriaPersonaRepository.delete(mp);
    }


    //VICEDIRECTOR


}
