package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaEquipoResponse;
import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.equipo.EquipoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaEquipoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
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

    //ADMINISRADOR
    // agregar equipo a memoria
    public MemoriaEquipo agregarEquipoAMemoriaAdmin(Long oidMemoria, Long oidEquipo) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Equipo equipo = equipoRepository.findById(oidEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        memoriaEquipoRepository.findByMemoriaAndOidEquipo(memoria, oidEquipo)
                .ifPresent(me -> {
                    throw new RuntimeException("El equipo ya está asociado a la memoria");
                });

        MemoriaEquipo memoriaEquipo = new MemoriaEquipo(memoria, equipo);
        return memoriaEquipoRepository.save(memoriaEquipo);
    }


    public List<MemoriaEquipoResponse> listarEquipoPorMemoriaAdmin(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaEquipoRepository.findByMemoria(memoria)
                .stream()
                .map(me -> new MemoriaEquipoResponse(
                        me.getOidEquipo(),
                        me.getDenominacion(),
                        me.getFechaIncorporacion(),
                        me.getMontoInvertido(),
                        me.getDescripcion()
                ))
                .toList();
    }

    // quitar equipo de una memoria
    public void quitarEquipoAMemoriaAdmin(Long oidMemoria, Long oidEquipo) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Equipo equipo = equipoRepository.findById(oidEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        MemoriaEquipo memoriaEquipo = memoriaEquipoRepository
                .findByMemoriaAndOidEquipo(memoria, oidEquipo)
                .orElseThrow(() -> new RuntimeException(
                        "El equipo no está asociado a la memoria"));

        memoriaEquipoRepository.delete(memoriaEquipo);
    }

    //DIRECTOR

    public MemoriaEquipo agregarEquipoAMemoriaDirector(
            Usuario usuario,
            Long oidMemoria,
            Long oidEquipo
    ) {

        // Validar grupo
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a un grupo");
        }

        // SOLO DIRECTOR
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException(
                    "Solo el Director puede modificar memorias");
        }

        Long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() ->
                        new RuntimeException("Memoria no encontrada"));

        // Validar grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Buscar equipo
        Equipo equipo = equipoRepository.findById(oidEquipo)
                .orElseThrow(() ->
                        new RuntimeException("Equipo no encontrado"));

        // Validar grupo del equipo
        if (equipo.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("El equipo no pertenece a su grupo");
        }

        // Validar repetido
        memoriaEquipoRepository
                .findByMemoriaAndOidEquipo(memoria, oidEquipo)
                .ifPresent(me -> {
                    throw new RuntimeException(
                            "El equipo ya está en la memoria");
                });

        // Guardar
        MemoriaEquipo memoriaEquipo =
                new MemoriaEquipo(memoria, equipo);

        return memoriaEquipoRepository.save(memoriaEquipo);
    }



    public List<MemoriaEquipoResponse> listarEquipoPorMemoriaDirector(
            Usuario usuario,
            Long oidMemoria
    ) {

        // Validar que tenga persona y grupo
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol (solo Director)
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException("Solo el director puede ver los equipos");
        }

        Long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        // Validar que sea de su grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Listar equipos
        return memoriaEquipoRepository.findByMemoria(memoria)
                .stream()
                .map(me -> new MemoriaEquipoResponse(
                        me.getOidEquipo(),
                        me.getDenominacion(),
                        me.getFechaIncorporacion(),
                        me.getMontoInvertido(),
                        me.getDescripcion()
                ))
                .toList();
    }


    public void quitarEquipoAMemoriaDirector(
            Usuario usuario,
            Long oidMemoria,
            Long oidEquipo
    ) {

        // Validar usuario con grupo
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException("Solo el director puede quitar equipos");
        }

        Long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        // Validar que la memoria sea del grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Buscar relación Memoria-Equipo
        MemoriaEquipo memoriaEquipo = memoriaEquipoRepository
                .findByMemoriaAndOidEquipo(memoria, oidEquipo)
                .orElseThrow(() ->
                        new RuntimeException("El equipo no está asociado a la memoria"));

        // Eliminar relación
        memoriaEquipoRepository.delete(memoriaEquipo);
    }



}
