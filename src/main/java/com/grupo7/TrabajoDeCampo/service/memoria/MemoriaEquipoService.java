package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.model.*;
import com.grupo7.TrabajoDeCampo.repository.memoria.*;
import com.grupo7.TrabajoDeCampo.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoriaEquipoService {

    private final MemoriaEquipoRepository memoriaEquipoRepository;
    private final MemoriaRepository memoriaRepository;
    private final EquipoRepository equipoRepository;

    public MemoriaEquipoService(
            MemoriaEquipoRepository memoriaEquipoRepository,
            MemoriaRepository memoriaRepository,
            EquipoRepository equipoRepository) {
        this.memoriaEquipoRepository = memoriaEquipoRepository;
        this.memoriaRepository = memoriaRepository;
        this.equipoRepository = equipoRepository;
    }

    public MemoriaEquipo agregarEquipo(Long oidMemoria, Long oidEquipo) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Equipo equipo = equipoRepository.findById(oidEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        MemoriaEquipo me = new MemoriaEquipo(memoria, equipo);
        return memoriaEquipoRepository.save(me);
    }

    public List<MemoriaEquipo> listarPorMemoria(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaEquipoRepository.findByMemoria(memoria);
    }
}
