package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.model.*;
import com.grupo7.TrabajoDeCampo.repository.*;
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

    public MemoriaPersona agregarPersona(
            Long oidMemoria,
            Long oidPersona,
            String rol,
            Integer horas) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Persona persona = personaRepository.findById(oidPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        MemoriaPersona mp = new MemoriaPersona(memoria, persona, rol, horas);
        return memoriaPersonaRepository.save(mp);
    }

    public List<MemoriaPersona> listarPorMemoria(Long oidMemoria) {
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaPersonaRepository.findByMemoria(memoria);
    }
}
