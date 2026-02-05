package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.memoria.MemoriaResponseAdministrador;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;

@Service
public class MemoriaService {

    private final MemoriaRepository memoriaRepository;
    private final GrupoRepository grupoRepository;

    public MemoriaService(MemoriaRepository memoriaRepository, GrupoRepository grupoRepository) {
        this.memoriaRepository = memoriaRepository;
        this.grupoRepository = grupoRepository;
    }

    public Memoria crearMemoria(Long oidGrupo, Integer anio) {
        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        memoriaRepository.findByGrupoAndAnio(grupo, anio)
                .ifPresent(m -> {
                    throw new RuntimeException("Ya existe una memoria para ese año");
                });

        Memoria memoria = new Memoria(
                new Timestamp(System.currentTimeMillis()),
                anio,
                grupo
        );

        return memoriaRepository.save(memoria);
    }


    public List<MemoriaResponseAdministrador> listarPorGrupo(Long oidGrupo) {

        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        return memoriaRepository.findByGrupo(grupo)
                .stream()
                .map(m -> new MemoriaResponseAdministrador(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion().toInstant(),
                        grupo.getOidGrupo(),
                        grupo.getNombreGrupo()
                ))
                .toList();
    }


    public MemoriaResponseAdministrador obtenerPorId(Long oidMemoria) {

        Memoria m = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return new MemoriaResponseAdministrador(
                m.getOidMemoria(),
                m.getAnio(),
                m.getFechaCreacion().toInstant(),
                m.getGrupo().getOidGrupo(),
                m.getGrupo().getNombreGrupo()
        );
    }

}
